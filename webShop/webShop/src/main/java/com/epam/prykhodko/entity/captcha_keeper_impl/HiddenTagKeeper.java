package com.epam.prykhodko.entity.captcha_keeper_impl;

import com.epam.prykhodko.entity.CaptchaKeeper;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HiddenTagKeeper implements CaptchaKeeper {

    @Override
    public void save(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String key, String value) {
        ServletContext servletContext = httpServletRequest.getServletContext();
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute("captchaKey");
        session.removeAttribute("captchaValue");
        Map<String, String> captchaKeys = (Map<String, String>) servletContext.getAttribute("captchaKeys");
        captchaKeys.put(key, value);
    }

    @Override
    public String get(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getParameter("captchaKey");
    }
}
