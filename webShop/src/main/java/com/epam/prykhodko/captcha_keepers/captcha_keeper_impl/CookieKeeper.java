package com.epam.prykhodko.captcha_keepers.captcha_keeper_impl;

import static com.epam.prykhodko.constants.Constants.CAPTCHA_KEY;
import static com.epam.prykhodko.constants.Constants.CAPTCHA_KEYS;

import com.epam.prykhodko.captcha_keepers.CaptchaKeeper;
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
        httpServletResponse.addCookie(new Cookie(CAPTCHA_KEY, key));
        ServletContext servletContext = httpServletRequest.getServletContext();
        Map<String, String> captchaKeys = (Map<String, String>) servletContext.getAttribute(CAPTCHA_KEYS);
        captchaKeys.put(key, value);
    }

    @Override
    public String get(HttpServletRequest httpServletRequest) {
        List<Cookie> cookies = Arrays.asList(httpServletRequest.getCookies());
        Optional<Cookie> cookie = cookies.stream().filter(c -> CAPTCHA_KEY.equals(c.getName())).findFirst();
        if (cookie.isPresent()) {
            Cookie key = cookie.get();
            String value = key.getValue();
            key.setMaxAge(0);
            return value;
        }
        return "";
    }
}
