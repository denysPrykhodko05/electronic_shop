package com.epam.prykhodko.entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CaptchaKeeper {

    void save(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String key, String value);
    String get(HttpServletRequest httpServletRequest);
}
