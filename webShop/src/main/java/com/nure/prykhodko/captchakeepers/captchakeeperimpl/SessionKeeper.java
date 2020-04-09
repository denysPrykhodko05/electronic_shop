package com.nure.prykhodko.captchakeepers.captchakeeperimpl;

import com.nure.prykhodko.captchakeepers.CaptchaKeeper;
import com.nure.prykhodko.constants.ApplicationConstants;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionKeeper implements CaptchaKeeper {

    @Override
    public void save(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Long key, String value) {
        HttpSession session = httpServletRequest.getSession();
        ServletContext servletContext = httpServletRequest.getServletContext();
        session.setAttribute(ApplicationConstants.CAPTCHA_KEY, key);
        Map<Long, String> captchaKeys = (Map<Long, String>) servletContext.getAttribute(ApplicationConstants.CAPTCHA_KEYS);
        captchaKeys.put(key, value);
    }

    @Override
    public Long get(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        return (Long) session.getAttribute(ApplicationConstants.CAPTCHA_KEY);
    }
}
