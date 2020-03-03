package com.epam.prykhodko.captcha_keepers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CaptchaKeeper {

    void save(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Long key, String value);

    Long get(HttpServletRequest httpServletRequest);
}
