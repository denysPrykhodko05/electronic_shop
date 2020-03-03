package com.epam.prykhodko.captchakeepers.captchakeeperimpl;

import static com.epam.prykhodko.constants.Constants.CAPTCHA_KEY;
import static com.epam.prykhodko.constants.Constants.CAPTCHA_KEYS;
import static com.epam.prykhodko.constants.Constants.CAPTCHA_VALUE;

import com.epam.prykhodko.captchakeepers.CaptchaKeeper;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HiddenFieldKeeper implements CaptchaKeeper {

    @Override
    public void save(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Long key, String value) {
        ServletContext servletContext = httpServletRequest.getServletContext();
        HttpSession session = httpServletRequest.getSession();
        httpServletRequest.setAttribute("hiddenFieldValue",key);
        session.removeAttribute(CAPTCHA_KEY);
        session.removeAttribute(CAPTCHA_VALUE);
        Map<Long, String> captchaKeys = (Map<Long, String>) servletContext.getAttribute(CAPTCHA_KEYS);
        captchaKeys.put(key, value);
    }

    @Override
    public Long get(HttpServletRequest httpServletRequest) {
        return Long.valueOf(httpServletRequest.getParameter(CAPTCHA_KEY));
    }
}
