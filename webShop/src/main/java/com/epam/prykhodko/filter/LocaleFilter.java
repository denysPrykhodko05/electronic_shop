package com.epam.prykhodko.filter;

import static com.epam.prykhodko.constants.ApplicationConstants.COOKIE;
import static com.epam.prykhodko.constants.ApplicationConstants.LOCALE_KEEPERS;

import com.epam.prykhodko.localekeepers.LocaleKeeper;
import com.epam.prykhodko.localekeepers.localekeeperimpl.SessionLocaleKeeper;
import com.epam.prykhodko.wrapper.LocalizationWrapper;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
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

public class LocaleFilter implements Filter {

    private LocaleKeeper localeKeeper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        ServletContext servletContext = httpServletRequest.getServletContext();
        LocalizationWrapper localizationWrapper = new LocalizationWrapper(httpServletRequest, httpServletResponse, localeKeeper);
        String language = httpServletRequest.getParameter("lang");
        List<String> localeList = (List<String>) servletContext.getAttribute("locales");
        httpServletRequest.setAttribute("locales", localeList);

        if (Objects.nonNull(language)) {
            session.setAttribute("localization", language);
            chain.doFilter(localizationWrapper, response);
            return;
        }
        chain.doFilter(localizationWrapper, response);
    }


    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext servletContext = filterConfig.getServletContext();
        Map<String, LocaleKeeper> localeKeeperMap = (Map<String, LocaleKeeper>) servletContext.getAttribute(LOCALE_KEEPERS);
        localeKeeper = localeKeeperMap.getOrDefault(COOKIE, new SessionLocaleKeeper());
    }

    @Override
    public void destroy() {

    }
}
