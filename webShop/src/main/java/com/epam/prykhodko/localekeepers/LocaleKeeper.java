package com.epam.prykhodko.localekeepers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LocaleKeeper {
    void save(HttpServletRequest request, HttpServletResponse response, String locale);
}
