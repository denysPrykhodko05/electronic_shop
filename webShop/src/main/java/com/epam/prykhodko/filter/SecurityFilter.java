package com.epam.prykhodko.filter;

import static com.epam.prykhodko.constants.ApplicationConstants.ACCESS_MANAGER;
import static com.epam.prykhodko.constants.ApplicationConstants.PREVIOUS_URL;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_SERVICE;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.INFO_SECURITY_FILTER_DESTROY;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.INFO_SECURITY_FILTER_INIT;

import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.mananger.AccessManager;
import com.epam.prykhodko.service.UserService;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class SecurityFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(SecurityFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = ((HttpServletRequest) request).getSession();
        ServletContext servletContext = request.getServletContext();
        String url = httpServletRequest.getRequestURI();
        String userLogin = (String) session.getAttribute(USER_LOGIN);
        AccessManager accessManager = (AccessManager) servletContext.getAttribute(ACCESS_MANAGER);

        if (!accessManager.checkUrl(url)) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        if (Objects.isNull(userLogin)) {
            session.setAttribute(PREVIOUS_URL, url);
            httpServletResponse.sendRedirect("/login");
            return;
        }

        UserService userService = (UserService) servletContext.getAttribute(USER_SERVICE);
        User user = userService.getUser(userLogin);

        if (accessManager.checkAccess(user.getRole(), url)) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info(INFO_SECURITY_FILTER_INIT);
    }

    @Override
    public void destroy() {
        LOGGER.info(INFO_SECURITY_FILTER_DESTROY);
    }
}
