package com.nure.prykhodko.localekeepers.localekeeperimpl;

import com.nure.prykhodko.localekeepers.LocaleKeeper;
import com.nure.prykhodko.constants.ApplicationConstants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionLocaleKeeper implements LocaleKeeper {

    @Override
    public void save(HttpServletRequest request, HttpServletResponse response, String locale) {
        HttpSession session = request.getSession();
        session.setAttribute(ApplicationConstants.LOCALIZATION, locale);
    }
}
