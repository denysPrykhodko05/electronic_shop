package com.nure.prykhodko.wrapper;

import com.nure.prykhodko.localekeepers.LocaleKeeper;
import com.nure.prykhodko.constants.ApplicationConstants;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LocalizationWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest servletRequest;
    private HttpServletResponse response;
    private LocaleKeeper localeKeeper;

    public LocalizationWrapper(HttpServletRequest request, HttpServletResponse response,
        LocaleKeeper localeKeeper) {
        super(request);
        this.servletRequest = request;
        this.response = response;
        this.localeKeeper = localeKeeper;
    }

    @Override
    public Locale getLocale() {
        Locale locale = getLocaleFromStorage(servletRequest);

        if (Objects.isNull(locale)) {
            locale = getLocaleFromBrowser(servletRequest);
        }

        return locale;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return new Enumeration<Locale>() {
            @Override
            public boolean hasMoreElements() {
                return true;
            }

            @Override
            public Locale nextElement() {
                return getLocale();
            }
        };
    }

    private Locale getLocaleFromStorage(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        ServletContext servletContext = request.getServletContext();
        HttpSession session = request.getSession();
        String keepsTime = servletContext.getInitParameter(ApplicationConstants.LOCALE_SAVE_TIME);
        String locale;

        if (Objects.nonNull(cookies)) {
            Optional<Cookie> cookie = Arrays.stream(cookies).filter(c -> c.getName().equals(ApplicationConstants.LOCALIZATION)).findFirst();

            if (cookie.isPresent()) {
                locale = cookie.get().getValue();
                cookie.get().setMaxAge(Integer.parseInt(keepsTime));
                return new Locale(locale);
            }
        }

        locale = (String) session.getAttribute(ApplicationConstants.LOCALIZATION);

        if (Objects.nonNull(locale)) {
            return new Locale(locale);
        }

        return null;
    }

    private Locale getLocaleFromBrowser(HttpServletRequest httpServletRequest) {
        ServletContext servletContext = httpServletRequest.getServletContext();
        HttpSession session = httpServletRequest.getSession();
        String[] applicationLocale = servletContext.getInitParameter(ApplicationConstants.APPLICATION_LOCALE).split(", ");
        Enumeration<Locale> browserLocales = httpServletRequest.getLocales();
        Locale locale;

        while (browserLocales.hasMoreElements()) {
            locale = browserLocales.nextElement();

            if (Arrays.asList(applicationLocale).contains(locale.getLanguage())) {
                session.setAttribute(ApplicationConstants.APP_LOCALE, locale);
                return locale;
            }
        }

        localeKeeper.save(httpServletRequest, response, applicationLocale[0]);
        return new Locale(applicationLocale[0]);
    }
}
