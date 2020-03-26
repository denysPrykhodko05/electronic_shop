package com.epam.prykhodko.localekeepers.localekeeperimpl;

import static com.epam.prykhodko.constants.ApplicationConstants.LOCALIZATION;

import com.epam.prykhodko.localekeepers.LocaleKeeper;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieLocaleKeeper implements LocaleKeeper {

    @Override
    public void save(HttpServletRequest request, HttpServletResponse response, String locale) {
        Cookie cookie = new Cookie(LOCALIZATION, locale);
        cookie.setMaxAge(10);
        response.addCookie(cookie);
    }
}
