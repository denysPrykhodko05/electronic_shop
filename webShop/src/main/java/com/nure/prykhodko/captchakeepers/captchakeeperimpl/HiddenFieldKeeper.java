package com.nure.prykhodko.captchakeepers.captchakeeperimpl;

import com.nure.prykhodko.captchakeepers.CaptchaKeeper;
import com.nure.prykhodko.constants.ApplicationConstants;
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
        session.removeAttribute(ApplicationConstants.CAPTCHA_KEY);
        session.removeAttribute(ApplicationConstants.CAPTCHA_VALUE);
        Map<Long, String> captchaKeys = (Map<Long, String>) servletContext.getAttribute(ApplicationConstants.CAPTCHA_KEYS);
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
