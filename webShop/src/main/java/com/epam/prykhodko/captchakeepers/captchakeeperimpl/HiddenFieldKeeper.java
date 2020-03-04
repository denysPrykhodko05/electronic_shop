package com.epam.prykhodko.captchakeepers.captchakeeperimpl;

import static com.epam.prykhodko.constants.ApplicationConstants.CAPTCHA_KEY;
import static com.epam.prykhodko.constants.ApplicationConstants.CAPTCHA_KEYS;
import static com.epam.prykhodko.constants.ApplicationConstants.CAPTCHA_VALUE;

import com.epam.prykhodko.captchakeepers.CaptchaKeeper;
import java.util.Map;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HiddenFieldKeeper implements CaptchaKeeper {

    @Override
    public void save(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Long key, String value) {
        ServletContext servletContext = httpServletRequest.getServletContext();
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute(CAPTCHA_KEY);
        session.removeAttribute(CAPTCHA_VALUE);
        Map<Long, String> captchaKeys = (Map<Long, String>) servletContext.getAttribute(CAPTCHA_KEYS);
        captchaKeys.put(key, value);
    }

    @Override
    public Long get(HttpServletRequest httpServletRequest) {
        String value = httpServletRequest.getParameter("hiddenField");
        if (Objects.isNull(value)) {
            return 0L;
        }
        return Long.valueOf(value);
    }
}
