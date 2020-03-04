package com.epam.prykhodko.captchakeepers.captchakeeperimpl;

import static com.epam.prykhodko.constants.ApplicationConstants.CAPTCHA_KEY;
import static com.epam.prykhodko.constants.ApplicationConstants.CAPTCHA_KEYS;

import com.epam.prykhodko.captchakeepers.CaptchaKeeper;
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
        session.setAttribute(CAPTCHA_KEY, key);
        Map<Long, String> captchaKeys = (Map<Long, String>) servletContext.getAttribute(CAPTCHA_KEYS);
        captchaKeys.put(key, value);
    }

    @Override
    public Long get(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        return (Long) session.getAttribute(CAPTCHA_KEY);
    }
}
