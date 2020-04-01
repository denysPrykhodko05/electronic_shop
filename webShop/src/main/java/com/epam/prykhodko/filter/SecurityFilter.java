package com.epam.prykhodko.filter;

import static com.epam.prykhodko.constants.LoggerMessagesConstants.INFO_SECURITY_FILTER_DESTROY;

import com.epam.prykhodko.mananger.AccessManager;
import com.epam.prykhodko.util.XMLParser;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class SecurityFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(SecurityFilter.class);
    private Map<String, List<String>> urlMap;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        AccessManager accessManager = new AccessManager(httpServletRequest, httpServletResponse, urlMap);

        if (accessManager.checkAccess()) {
            chain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        String path = servletContext.getInitParameter("securityFilePath");
        urlMap = XMLParser.securityXMLParse(path);
    }

    @Override
    public void destroy() {
        LOGGER.info(INFO_SECURITY_FILTER_DESTROY);
    }
}
