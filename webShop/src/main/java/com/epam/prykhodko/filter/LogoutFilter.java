package com.epam.prykhodko.filter;

import static com.epam.prykhodko.constants.ApplicationConstants.LOGIN;
import static com.epam.prykhodko.constants.LoggerMessageConstants.INFO_LOG_OUT_FILTER_DESTROY;
import static com.epam.prykhodko.constants.LoggerMessageConstants.INFO_LOG_OUT_FILTER_START;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class LogoutFilter implements Filter {

    private final Logger LOGGER = Logger.getLogger(LogoutFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info(INFO_LOG_OUT_FILTER_START);
    }

    @Override
    public void destroy() {
        LOGGER.info(INFO_LOG_OUT_FILTER_DESTROY);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute(LOGIN);
        httpServletResponse.sendRedirect("/");
    }
}
