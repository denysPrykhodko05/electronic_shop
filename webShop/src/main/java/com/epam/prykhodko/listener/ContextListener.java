package com.epam.prykhodko.listener;

import static com.epam.prykhodko.constants.ApplicationConstants.CAPTCHA;
import static com.epam.prykhodko.constants.ApplicationConstants.CAPTCHA_KEEPER;
import static com.epam.prykhodko.constants.ApplicationConstants.CAPTCHA_KEYS;
import static com.epam.prykhodko.constants.ApplicationConstants.CAPTCHA_TIME;
import static com.epam.prykhodko.constants.ApplicationConstants.COOKIE;
import static com.epam.prykhodko.constants.ApplicationConstants.HIDDEN_FIELD;
import static com.epam.prykhodko.constants.ApplicationConstants.KEEPERS;
import static com.epam.prykhodko.constants.ApplicationConstants.REG_FORM;
import static com.epam.prykhodko.constants.ApplicationConstants.SESSION;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_SERVICE;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_UTILS;
import static com.epam.prykhodko.constants.ApplicationConstants.VALIDATOR;

import com.epam.prykhodko.bean.RegFormBean;
import com.epam.prykhodko.captchakeepers.CaptchaKeeper;
import com.epam.prykhodko.captchakeepers.captchakeeperimpl.CookieKeeper;
import com.epam.prykhodko.captchakeepers.captchakeeperimpl.HiddenFieldKeeper;
import com.epam.prykhodko.captchakeepers.captchakeeperimpl.SessionKeeper;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.repository.UserRepository;
import com.epam.prykhodko.repository.impl.UserRepositoryImpl;
import com.epam.prykhodko.service.UserService;
import com.epam.prykhodko.service.impl.UserServiceImpl;
import com.epam.prykhodko.util.TimerThread;
import com.epam.prykhodko.util.UserUtils;
import com.epam.prykhodko.util.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);
    Map<Long, String> captchaKeys = new HashMap<>();
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        BasicConfigurator.configure();
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository);
        String capthaKeeper = servletContextEvent.getServletContext().getInitParameter(CAPTCHA);
        String captchaTime = servletContextEvent.getServletContext().getInitParameter(CAPTCHA_TIME);
        CaptchaKeeper captchaKeeper = createKeeper(capthaKeeper);
        Validator validator = new Validator();
        UserUtils userUtils = new UserUtils();
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(CAPTCHA_KEYS, captchaKeys);
        Map<String, CaptchaKeeper> keepers = new HashMap<>();
        executorService.scheduleWithFixedDelay(new TimerThread(captchaKeys), 0, Long.parseLong(captchaTime), TimeUnit.SECONDS);
        keepers.put(SESSION, new SessionKeeper());
        keepers.put(COOKIE, new CookieKeeper());
        keepers.put(HIDDEN_FIELD, new HiddenFieldKeeper());
        servletContext.setAttribute(KEEPERS, keepers);
        servletContext.setAttribute(VALIDATOR, validator);
        servletContext.setAttribute(USER_UTILS, userUtils);
        servletContext.setAttribute(CAPTCHA_KEEPER, captchaKeeper);
        servletContext.setAttribute(REG_FORM, new RegFormBean());
        userService.add(new User(1,"Ivan", "Ivanov", "ivan@gmail.com", "login", "Aadaf@12",1));
        userService.add(new User(2,"Peter", "Petrov", "peter@gmail.com", "peterPeter", "Asaba_33",1));
        servletContext.setAttribute(USER_SERVICE, userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        executorService.shutdownNow();
        LOGGER.info("context destroyer");
    }

    private CaptchaKeeper createKeeper(String keeper) {
        if (COOKIE.equals(keeper)) {
            return new CookieKeeper();
        }

        if (HIDDEN_FIELD.equals(keeper)) {
            return new HiddenFieldKeeper();
        }

        return new SessionKeeper();
    }
}
