package com.epam.prykhodko.filter;

import static com.epam.prykhodko.constants.ApplicationConstants.APPLICATION_LOCALE;
import static com.epam.prykhodko.constants.ApplicationConstants.COOKIE;
import static com.epam.prykhodko.constants.ApplicationConstants.LANG;
import static com.epam.prykhodko.constants.ApplicationConstants.LOCALES;
import static com.epam.prykhodko.constants.ApplicationConstants.LOCALE_KEEPERS;
import static com.epam.prykhodko.constants.ApplicationConstants.LOCALIZATION;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.INFO_LOCALE_FILTER_DESTROY;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.INFO_LOCALE_FILTER_INIT;

import com.epam.prykhodko.localekeepers.LocaleKeeper;
import com.epam.prykhodko.localekeepers.localekeeperimpl.SessionLocaleKeeper;
import com.epam.prykhodko.wrapper.LocalizationWrapper;
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

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        ServletContext servletContext = httpServletRequest.getServletContext();
        LocalizationWrapper localizationWrapper = new LocalizationWrapper(httpServletRequest, httpServletResponse, localeKeeper);
        String language = httpServletRequest.getParameter(LANG);
        List<String> localeList = Arrays.asList(servletContext.getInitParameter(APPLICATION_LOCALE).split(", "));
        httpServletRequest.setAttribute(LOCALES, localeList);

        if (Objects.nonNull(language)) {
            session.setAttribute(LOCALIZATION, language);
        }
        chain.doFilter(localizationWrapper, response);
    }


    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info(INFO_LOCALE_FILTER_INIT);
        ServletContext servletContext = filterConfig.getServletContext();
        Map<String, LocaleKeeper> localeKeeperMap = (Map<String, LocaleKeeper>) servletContext.getAttribute(LOCALE_KEEPERS);
        localeKeeper = localeKeeperMap.getOrDefault(COOKIE, new SessionLocaleKeeper());
    }

    @Override
    public void destroy() {
        LOGGER.info(INFO_LOCALE_FILTER_DESTROY);
    }
}
