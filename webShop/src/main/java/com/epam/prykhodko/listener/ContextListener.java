package com.epam.prykhodko.listener;

import static com.epam.prykhodko.constants.ApplicationConstants.CAPTCHA;
import static com.epam.prykhodko.constants.ApplicationConstants.CAPTCHA_KEEPER;
import static com.epam.prykhodko.constants.ApplicationConstants.CAPTCHA_KEYS;
import static com.epam.prykhodko.constants.ApplicationConstants.CAPTCHA_TIME;
import static com.epam.prykhodko.constants.ApplicationConstants.CONNECTION_MANAGER;
import static com.epam.prykhodko.constants.ApplicationConstants.CONTEXT_DESTROYER;
import static com.epam.prykhodko.constants.ApplicationConstants.COOKIE;
import static com.epam.prykhodko.constants.ApplicationConstants.HIDDEN;
import static com.epam.prykhodko.constants.ApplicationConstants.HIDDEN_FIELD;
import static com.epam.prykhodko.constants.ApplicationConstants.IMAGE_DRAW;
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
import com.epam.prykhodko.dao.impl.UserDAO;
import com.epam.prykhodko.mananger.ConnectionManager;
import com.epam.prykhodko.service.UserService;
import com.epam.prykhodko.service.userservicedaoimpl.UserServiceDAOImpl;
import com.epam.prykhodko.util.ImageDraw;
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
    private final Map<Long, String> captchaKeys = new HashMap<>();
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final UserDAO userDAO = new UserDAO();
    private final Validator validator = new Validator();
    private final UserUtils userUtils = new UserUtils();
    private final ImageDraw imageDraw = new ImageDraw();
    private final ConnectionManager connectionManager = new ConnectionManager();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        BasicConfigurator.configure();
        String capthaKeeper = servletContextEvent.getServletContext().getInitParameter(CAPTCHA);
        String captchaTime = servletContextEvent.getServletContext().getInitParameter(CAPTCHA_TIME);
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(CAPTCHA_KEYS, captchaKeys);
        Map<String, CaptchaKeeper> keepers = new HashMap<>();
        executorService.scheduleWithFixedDelay(new TimerThread(captchaKeys), 0, Long.parseLong(captchaTime), TimeUnit.SECONDS);
        keepers.put(SESSION, new SessionKeeper());
        keepers.put(COOKIE, new CookieKeeper());
        keepers.put(HIDDEN_FIELD, new HiddenFieldKeeper());
        CaptchaKeeper captchaKeeper = createKeeper(capthaKeeper, servletContextEvent.getServletContext());
        servletContext.setAttribute(KEEPERS, keepers);
        servletContext.setAttribute(VALIDATOR, validator);
        servletContext.setAttribute(USER_UTILS, userUtils);
        servletContext.setAttribute(CAPTCHA_KEEPER, captchaKeeper);
        servletContext.setAttribute(REG_FORM, new RegFormBean());
        servletContext.setAttribute(IMAGE_DRAW, imageDraw);
        servletContext.setAttribute(CONNECTION_MANAGER, connectionManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        executorService.shutdownNow();
        LOGGER.info(CONTEXT_DESTROYER);
    }

    private CaptchaKeeper createKeeper(String keeper, ServletContext servletContext) {
        servletContext.setAttribute(HIDDEN, false);
        if (COOKIE.equals(keeper)) {
            return new CookieKeeper();
        }

        if (HIDDEN_FIELD.equals(keeper)) {
            servletContext.setAttribute(HIDDEN, true);
            return new HiddenFieldKeeper();
        }

        return new SessionKeeper();
    }
}
