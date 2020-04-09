package com.nure.prykhodko.filter;

import com.nure.prykhodko.constants.ApplicationConstants;
import com.nure.prykhodko.constants.LoggerMessagesConstants;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class CacheFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(CacheFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.setHeader(ApplicationConstants.CACHE_CONTROL, ApplicationConstants.NO_CACHE);
        filterChain.doFilter(servletRequest, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info(LoggerMessagesConstants.INFO_CACHE_FILTER_INIT);
    }

    @Override
    public void destroy() {
        LOGGER.info(LoggerMessagesConstants.INFO_CACHE_FILTER_DESTROY);
    }
}
