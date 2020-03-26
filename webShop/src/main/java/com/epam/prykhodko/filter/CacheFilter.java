package com.epam.prykhodko.filter;

import static com.epam.prykhodko.constants.ApplicationConstants.CACHE_CONTROL;
import static com.epam.prykhodko.constants.ApplicationConstants.NO_CACHE;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.INFO_CACHE_FILTER_DESTROY;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.INFO_CACHE_FILTER_INIT;

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
        res.setHeader(CACHE_CONTROL, NO_CACHE);
        filterChain.doFilter(servletRequest, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info(INFO_CACHE_FILTER_INIT);
    }

    @Override
    public void destroy() {
        LOGGER.info(INFO_CACHE_FILTER_DESTROY);
    }
}
