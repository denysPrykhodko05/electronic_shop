package com.nure.prykhodko.listener;

import com.nure.prykhodko.bean.RegFormBean;
import com.nure.prykhodko.captchakeepers.CaptchaKeeper;
import com.nure.prykhodko.captchakeepers.captchakeeperimpl.CookieKeeper;
import com.nure.prykhodko.captchakeepers.captchakeeperimpl.HiddenFieldKeeper;
import com.nure.prykhodko.captchakeepers.captchakeeperimpl.SessionKeeper;
import com.nure.prykhodko.dao.OrderDAO;
import com.nure.prykhodko.dao.ProductDAO;
import com.nure.prykhodko.dao.UserDAO;
import com.nure.prykhodko.dao.impl.OrderDAOImpl;
import com.nure.prykhodko.dao.impl.ProductDAOImpl;
import com.nure.prykhodko.dao.impl.UserDAOImpl;
import com.nure.prykhodko.handler.TransactionHandler;
import com.nure.prykhodko.localekeepers.LocaleKeeper;
import com.nure.prykhodko.localekeepers.localekeeperimpl.CookieLocaleKeeper;
import com.nure.prykhodko.localekeepers.localekeeperimpl.SessionLocaleKeeper;
import com.nure.prykhodko.mananger.AccessManager;
import com.nure.prykhodko.mananger.ConnectionManager;
import com.nure.prykhodko.service.OrderService;
import com.nure.prykhodko.service.ProductService;
import com.nure.prykhodko.service.UserService;
import com.nure.prykhodko.service.orderserviceimpl.OrderServiceImpl;
import com.nure.prykhodko.service.productservicedaoimpl.MysqlProductServiceImpl;
import com.nure.prykhodko.service.userservicedaoimpl.MysqlUserServiceImpl;
import com.nure.prykhodko.util.ImageDraw;
import com.nure.prykhodko.util.TimerThread;
import com.nure.prykhodko.util.UserUtils;
import com.nure.prykhodko.util.Validator;
import com.nure.prykhodko.util.XMLParser;
import com.nure.prykhodko.constants.ApplicationConstants;
import java.util.HashMap;
import java.util.List;
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
    private final UserDAO userUserDAOImpl = new UserDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final TransactionHandler transactionHandler = new TransactionHandler(new ConnectionManager());
    private final UserService userService = new MysqlUserServiceImpl(userUserDAOImpl, transactionHandler);
    private final ProductService productService = new MysqlProductServiceImpl(productDAO, transactionHandler);
    private final OrderService orderService = new OrderServiceImpl(orderDAO, transactionHandler);
    private final Validator validator = new Validator();
    private final UserUtils userUtils = new UserUtils();
    private final ImageDraw imageDraw = new ImageDraw();
    private final ConnectionManager connectionManager = new ConnectionManager();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        BasicConfigurator.configure();
        String capthaKeeper = servletContextEvent.getServletContext().getInitParameter(ApplicationConstants.CAPTCHA);
        String captchaTime = servletContextEvent.getServletContext().getInitParameter(ApplicationConstants.CAPTCHA_TIME);
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(ApplicationConstants.CAPTCHA_KEYS, captchaKeys);
        Map<String, CaptchaKeeper> keepers = new HashMap<>();
        Map<String, List<String>> urlMap;
        executorService.scheduleWithFixedDelay(new TimerThread(captchaKeys), 0, Long.parseLong(captchaTime), TimeUnit.SECONDS);
        keepers.put(ApplicationConstants.SESSION, new SessionKeeper());
        keepers.put(ApplicationConstants.COOKIE, new CookieKeeper());
        keepers.put(ApplicationConstants.HIDDEN_FIELD, new HiddenFieldKeeper());
        CaptchaKeeper captchaKeeper = createKeeper(capthaKeeper, servletContextEvent.getServletContext());
        String path = servletContext.getInitParameter("securityFilePath");
        urlMap = XMLParser.securityXMLParse(path);
        AccessManager accessManager = new AccessManager(urlMap);
        servletContext.setAttribute(ApplicationConstants.KEEPERS, keepers);
        servletContext.setAttribute(ApplicationConstants.VALIDATOR, validator);
        servletContext.setAttribute(ApplicationConstants.USER_UTILS, userUtils);
        servletContext.setAttribute(ApplicationConstants.CAPTCHA_KEEPER, captchaKeeper);
        servletContext.setAttribute(ApplicationConstants.REG_FORM, new RegFormBean());
        servletContext.setAttribute(ApplicationConstants.IMAGE_DRAW, imageDraw);
        servletContext.setAttribute(ApplicationConstants.CONNECTION_MANAGER, connectionManager);
        servletContext.setAttribute(ApplicationConstants.USER_SERVICE, userService);
        servletContext.setAttribute(ApplicationConstants.PRODUCT_SERVICE, productService);
        servletContext.setAttribute(ApplicationConstants.ORDER_SERVICE, orderService);
        servletContext.setAttribute(ApplicationConstants.LOCALE_KEEPERS, createLocaleKeepers());
        servletContext.setAttribute(ApplicationConstants.ACCESS_MANAGER, accessManager);
        servletContext.setAttribute(ApplicationConstants.URL_MAP, urlMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        executorService.shutdownNow();
        LOGGER.info(ApplicationConstants.CONTEXT_DESTROYER);
    }

    private Map<String, LocaleKeeper> createLocaleKeepers() {
        Map<String, LocaleKeeper> localeKeeperMap = new HashMap<>();
        localeKeeperMap.put(ApplicationConstants.COOKIE, new CookieLocaleKeeper());
        localeKeeperMap.put(ApplicationConstants.SESSION, new SessionLocaleKeeper());
        return localeKeeperMap;
    }

    private CaptchaKeeper createKeeper(String keeper, ServletContext servletContext) {
        servletContext.setAttribute(ApplicationConstants.HIDDEN, false);
        if (ApplicationConstants.COOKIE.equals(keeper)) {
            return new CookieKeeper();
        }

        if (ApplicationConstants.HIDDEN_FIELD.equals(keeper)) {
            servletContext.setAttribute(ApplicationConstants.HIDDEN, true);
            return new HiddenFieldKeeper();
        }

        return new SessionKeeper();
    }
}
