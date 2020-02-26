package com.epam.prykhodko.entity.captcha_keeper_impl;

import com.epam.prykhodko.entity.CaptchaKeeper;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ContextKeeper implements CaptchaKeeper {

    @Override
    public void save(HttpServletRequest httpServletRequest, String key, String value) {

    }
}
