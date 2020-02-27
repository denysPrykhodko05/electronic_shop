package com.epam.prykhodko.captcha_keepers.captcha_keeper_impl;

import com.epam.prykhodko.captcha_keepers.CaptchaKeeper;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionKeeper implements CaptchaKeeper {

    @Override
    public void save(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String key, String value) {
        HttpSession session = httpServletRequest.getSession();
        ServletContext servletContext = httpServletRequest.getServletContext();
        session.setAttribute("captchaKey", key);
        Map<String, String> captchaKeys = (Map<String, String>) servletContext.getAttribute("captchaKeys");
        captchaKeys.put(key, value);
    }

    @Override
    public String get(HttpServletRequest httpServletRequest) {
        return (String) httpServletRequest.getSession().getAttribute("captchaKey");
    }
}
