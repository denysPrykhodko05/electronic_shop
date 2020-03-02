package com.epam.prykhodko.captcha_keepers.captchakeeperimpl;

import static com.epam.prykhodko.constants.Constants.CAPTCHA_KEY;
import static com.epam.prykhodko.constants.Constants.CAPTCHA_KEYS;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

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
    public void save(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Long key, String value) {
        httpServletResponse.addCookie(new Cookie(CAPTCHA_KEY, String.valueOf(key)));
        ServletContext servletContext = httpServletRequest.getServletContext();
        Map<Long, String> captchaKeys = (Map<Long, String>) servletContext.getAttribute(CAPTCHA_KEYS);
        captchaKeys.put(key, value);
    }

    @Override
    public Long get(HttpServletRequest httpServletRequest) {
        List<Cookie> cookies = Arrays.asList(httpServletRequest.getCookies());
        Optional<Cookie> cookie = cookies.stream().filter(c -> CAPTCHA_KEY.equals(c.getName())).findFirst();

        if (cookie.isPresent()) {
            Cookie key = cookie.get();
            Long value = Long.valueOf(key.getValue());
            key.setMaxAge(0);
            return value;
        }
        return Long.valueOf(INTEGER_ZERO);
    }
}
