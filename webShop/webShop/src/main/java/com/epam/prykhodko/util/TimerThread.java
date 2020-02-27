package com.epam.prykhodko.util;

import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class TimerThread implements Runnable {

    private final HttpSession session;
    private final String key;

    public TimerThread(HttpSession session, String key) {
        this.session = session;
        this.key = key;
    }

    @Override
    public void run() {
        ServletContext servletContext = session.getServletContext();
        Map<String, String> captchaKeys = (Map<String, String>) servletContext.getAttribute("captchaKeys");
        captchaKeys.remove(key);
        session.removeAttribute(key);
        if (session.getAttribute("timer") != null) {
            session.setAttribute("timer", false);
        }
    }
}
