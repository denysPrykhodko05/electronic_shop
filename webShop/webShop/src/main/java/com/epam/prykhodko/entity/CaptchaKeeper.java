package com.epam.prykhodko.entity;

import javax.servlet.http.HttpServletRequest;

public interface CaptchaKeeper {

    void save(HttpServletRequest httpServletRequest, String key, String value);
}
