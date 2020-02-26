package com.epam.prykhodko.entity.captcha_keeper_impl;

import com.epam.prykhodko.entity.CaptchaKeeper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HiddenFieldKeeper implements CaptchaKeeper {

    @Override
    public void save(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String key, String value) {
        
    }

    @Override
    public String get(HttpServletRequest httpServletRequest) {
        return null;
    }
}
