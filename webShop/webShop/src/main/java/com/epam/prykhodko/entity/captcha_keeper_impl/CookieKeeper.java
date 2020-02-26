package com.epam.prykhodko.entity.captcha_keeper_impl;

import com.epam.prykhodko.entity.CaptchaKeeper;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieKeeper implements CaptchaKeeper {

    @Override
    public void save(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String key, String value) {
        httpServletResponse.addCookie(new Cookie("captchaKey", key));
        ServletContext servletContext = httpServletRequest.getServletContext();
        Map<String, String> captchaKeys = (Map<String, String>) servletContext.getAttribute("captchaKeys");
        captchaKeys.put(key, value);
    }

    @Override
    public String get(HttpServletRequest httpServletRequest) {
        List<Cookie> cookies = Arrays.asList(httpServletRequest.getCookies());
        Optional<Cookie> cookie = cookies.stream().filter(c -> "captchaKey".equals(c.getName())).findFirst();
        if (cookie.isPresent()) {
            Cookie key = cookie.get();
            String value = key.getValue();
            key.setMaxAge(0);
            return value;
        }
        return "";
    }
}
