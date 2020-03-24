package com.epam.prykhodko.filter;

import com.epam.prykhodko.wrapper.LocalizationWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LocaleFilter implements Filter {

    private List<String> localeList;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        LocalizationWrapper localizationWrapper = new LocalizationWrapper(httpServletRequest);
        String language = httpServletRequest.getParameter("lang");
        httpServletRequest.setAttribute("locales", localeList);

        if (Objects.nonNull(language)) {
            session.setAttribute("localization", language);
            chain.doFilter(request, response);
            return;
        }

        localizationWrapper.getLocale();
        chain.doFilter(request, response);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        localeList = new ArrayList<>(Arrays.asList("en", "ru", "de"));
    }

    @Override
    public void destroy() {

    }
}
