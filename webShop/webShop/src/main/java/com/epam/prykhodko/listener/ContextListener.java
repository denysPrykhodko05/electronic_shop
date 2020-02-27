package com.epam.prykhodko.listener;

import com.epam.prykhodko.captcha_keepers.CaptchaKeeper;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.captcha_keepers.captcha_keeper_impl.CookieKeeper;
import com.epam.prykhodko.captcha_keepers.captcha_keeper_impl.HiddenTagKeeper;
import com.epam.prykhodko.captcha_keepers.captcha_keeper_impl.SessionKeeper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        Map<String, CaptchaKeeper> keepers = new HashMap<>();
        Map<String, String> captchaKeys = new LinkedHashMap<>();
        keepers.put("session", new SessionKeeper());
        keepers.put("cookie", new CookieKeeper());
        keepers.put("hiddenField", new HiddenTagKeeper());
        User user1 = new User("Ivan", "Ivanov", "ivan@gmail.com", "login", "Aadaf@12");
        User user2 = new User("Peter", "Petrov", "peter@gmail.com", "peterPeter", "Asaba_33");
        List<User> users = new ArrayList<>(Arrays.asList(user1, user2));
        servletContext.setAttribute("users", users);
        servletContext.setAttribute("keepers", keepers);
        servletContext.setAttribute("captchaKeys", captchaKeys);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
