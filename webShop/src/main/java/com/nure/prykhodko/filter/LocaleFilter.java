package com.nure.prykhodko.filter;

import com.nure.prykhodko.localekeepers.LocaleKeeper;
import com.nure.prykhodko.localekeepers.localekeeperimpl.SessionLocaleKeeper;
import com.nure.prykhodko.wrapper.LocalizationWrapper;
import com.nure.prykhodko.constants.ApplicationConstants;
import com.nure.prykhodko.constants.LoggerMessagesConstants;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

public class LocaleFilter implements Filter {

    public static final Logger LOGGER = Logger.getLogger(LocaleFilter.class);
    private LocaleKeeper localeKeeper;
    private List<String> localeList;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        LocalizationWrapper localizationWrapper = new LocalizationWrapper(httpServletRequest, httpServletResponse, localeKeeper);
        String language = httpServletRequest.getParameter(ApplicationConstants.LANG);

        httpServletRequest.setAttribute(ApplicationConstants.LOCALES, localeList);
        language = Objects.nonNull(language) ? language : localizationWrapper.getLocale().getLanguage();
        session.setAttribute(ApplicationConstants.LOCALIZATION, language);
        chain.doFilter(localizationWrapper, response);
    }


    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info(LoggerMessagesConstants.INFO_LOCALE_FILTER_INIT);
        ServletContext servletContext = filterConfig.getServletContext();
        Map<String, LocaleKeeper> localeKeeperMap = (Map<String, LocaleKeeper>) servletContext.getAttribute(ApplicationConstants.LOCALE_KEEPERS);
        localeKeeper = localeKeeperMap.getOrDefault(ApplicationConstants.COOKIE, new SessionLocaleKeeper());
        localeList = Arrays.asList(servletContext.getInitParameter(ApplicationConstants.APPLICATION_LOCALE).split(", "));
    }

    @Override
    public void destroy() {
        LOGGER.info(LoggerMessagesConstants.INFO_LOCALE_FILTER_DESTROY);
    }
}
