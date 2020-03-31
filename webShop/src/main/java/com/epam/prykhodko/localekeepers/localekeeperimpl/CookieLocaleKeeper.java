package com.epam.prykhodko.localekeepers.localekeeperimpl;

import static com.epam.prykhodko.constants.ApplicationConstants.LOCALE_SAVE_TIME;
import static com.epam.prykhodko.constants.ApplicationConstants.LOCALIZATION;

import com.epam.prykhodko.localekeepers.LocaleKeeper;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieLocaleKeeper implements LocaleKeeper {

    @Override
    public void save(HttpServletRequest request, HttpServletResponse response, String locale) {
        ServletContext servletContext = request.getServletContext();
        String liveTimeForCookie = servletContext.getInitParameter(LOCALE_SAVE_TIME);
        Cookie cookie = new Cookie(LOCALIZATION, locale);

        if (Objects.isNull(liveTimeForCookie)) {
            liveTimeForCookie = "3000";
        }

        cookie.setMaxAge(Integer.parseInt(liveTimeForCookie));
        response.addCookie(cookie);
    }
}
