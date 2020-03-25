package com.epam.prykhodko.localekeepers.localekeeperimpl;

import com.epam.prykhodko.localekeepers.LocaleKeeper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionLocaleKeeper implements LocaleKeeper {

    @Override
    public void save(HttpServletRequest request, HttpServletResponse response, String locale) {
        HttpSession session = request.getSession();
        session.setAttribute("localization", locale);
    }
}
