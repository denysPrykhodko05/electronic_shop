package com.epam.prykhodko.listener;

import static com.epam.prykhodko.constants.Constants.CAPTCHA_KEYS;
import static com.epam.prykhodko.constants.Constants.COOKIE;
import static com.epam.prykhodko.constants.Constants.HIDDEN_FIELD;
import static com.epam.prykhodko.constants.Constants.KEEPERS;
import static com.epam.prykhodko.constants.Constants.SESSION;
import static com.epam.prykhodko.constants.Constants.USERS;

import com.epam.prykhodko.captcha_keepers.CaptchaKeeper;
import com.epam.prykhodko.captcha_keepers.captcha_keeper_impl.CookieKeeper;
import com.epam.prykhodko.captcha_keepers.captcha_keeper_impl.HiddenFieldKeeper;
import com.epam.prykhodko.captcha_keepers.captcha_keeper_impl.SessionKeeper;
import com.epam.prykhodko.entity.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        BasicConfigurator.configure();
        ServletContext servletContext = servletContextEvent.getServletContext();
        Map<String, CaptchaKeeper> keepers = new HashMap<>();
        Map<String, String> captchaKeys = new LinkedHashMap<>();
        keepers.put(SESSION, new SessionKeeper());
        keepers.put(COOKIE, new CookieKeeper());
        keepers.put(HIDDEN_FIELD, new HiddenFieldKeeper());
        User user1 = new User("Ivan", "Ivanov", "ivan@gmail.com", "login", "Aadaf@12");
        User user2 = new User("Peter", "Petrov", "peter@gmail.com", "peterPeter", "Asaba_33");
        List<User> users = new ArrayList<>(Arrays.asList(user1, user2));
        servletContext.setAttribute(USERS, users);
        servletContext.setAttribute(KEEPERS, keepers);
        servletContext.setAttribute(CAPTCHA_KEYS, captchaKeys);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.info("context destroyer");
    }
}
