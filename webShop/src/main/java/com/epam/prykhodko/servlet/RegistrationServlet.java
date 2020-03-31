package com.epam.prykhodko.servlet;


import static com.epam.prykhodko.bean.RegFormBean.fromRequestToRegFormBean;
import static com.epam.prykhodko.constants.ApplicationConstants.CAPTCHA_KEEPER;
import static com.epam.prykhodko.constants.ApplicationConstants.CAPTCHA_KEY;
import static com.epam.prykhodko.constants.ApplicationConstants.CAPTCHA_KEYS;
import static com.epam.prykhodko.constants.ApplicationConstants.CO_PASSWORD;
import static com.epam.prykhodko.constants.ApplicationConstants.EMAIL;
import static com.epam.prykhodko.constants.ApplicationConstants.EMAIL_REGEX;
import static com.epam.prykhodko.constants.ApplicationConstants.ERRORS;
import static com.epam.prykhodko.constants.ApplicationConstants.IMAGE_DRAW;
import static com.epam.prykhodko.constants.ApplicationConstants.LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.LOGIN_REGEX;
import static com.epam.prykhodko.constants.ApplicationConstants.MAILS;
import static com.epam.prykhodko.constants.ApplicationConstants.NAME;
import static com.epam.prykhodko.constants.ApplicationConstants.NOT_USER_ERROR;
import static com.epam.prykhodko.constants.ApplicationConstants.PASSWORD;
import static com.epam.prykhodko.constants.ApplicationConstants.PASSWORD_REGEX;
import static com.epam.prykhodko.constants.ApplicationConstants.POLICY;
import static com.epam.prykhodko.constants.ApplicationConstants.REGISTRATION_JSP_LINK;
import static com.epam.prykhodko.constants.ApplicationConstants.SURNAME;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_DATA;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_PERSONAL_DATA_REGEX;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_SERVICE;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_UTILS;
import static com.epam.prykhodko.constants.ApplicationConstants.VALIDATOR;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_LOAD_FILE;
import static java.lang.System.currentTimeMillis;

import com.epam.prykhodko.bean.RegFormBean;
import com.epam.prykhodko.captchakeepers.CaptchaKeeper;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.service.UserService;
import com.epam.prykhodko.util.ImageDraw;
import com.epam.prykhodko.util.UserUtils;
import com.epam.prykhodko.util.Validator;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

@WebServlet("/registration.do")
public class RegistrationServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);
    private static final String FILE = "FILE";
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute(ERRORS);
        session.removeAttribute(USER_DATA);
        forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Map<String, String> errors = new LinkedHashMap<>();
        Map<String, String> userData = new LinkedHashMap<>();
        ServletContext servletContext = httpServletRequest.getServletContext();
        HttpSession session = httpServletRequest.getSession();
        RegFormBean formBean = null;
        userService = (UserService) servletContext.getAttribute(USER_SERVICE);
        UserUtils userUtils = (UserUtils) servletContext.getAttribute(USER_UTILS);
        Validator validator = (Validator) servletContext.getAttribute(VALIDATOR);
        ImageDraw imageDraw = (ImageDraw) servletContext.getAttribute(IMAGE_DRAW);
        CaptchaKeeper captchaKeeper = (CaptchaKeeper) servletContext.getAttribute(CAPTCHA_KEEPER);
        Map<Long, String> captchaKeys = (Map<Long, String>) servletContext.getAttribute(CAPTCHA_KEYS);

        try {
            formBean = fromRequestToRegFormBean(httpServletRequest);
        } catch (FileUploadException e) {
            errors.put(FILE, ERR_CANNOT_LOAD_FILE);
            LOGGER.error(ERR_CANNOT_LOAD_FILE);
            forward(httpServletRequest, httpServletResponse);
            return;
        }

        validator.checkField(NAME, formBean.getName(), USER_PERSONAL_DATA_REGEX, errors);
        validator.checkField(SURNAME, formBean.getSurname(), USER_PERSONAL_DATA_REGEX, errors);
        validator.checkField(LOGIN, formBean.getLogin(), LOGIN_REGEX, errors);
        validator.checkField(EMAIL, formBean.getEmail(), EMAIL_REGEX, errors);
        validator.checkField(PASSWORD, formBean.getPassword(), PASSWORD_REGEX, errors);
        validator.checkField(CO_PASSWORD, formBean.getPassword(), formBean.getConfirmPassword(), errors);
        validator.checkCaptcha(captchaKeys, captchaKeeper.get(httpServletRequest), formBean.getCaptcha(), errors);
        validator.checkCheckbox(POLICY, formBean.getPolicy(), errors);
        validator.checkCheckbox(MAILS, formBean.getMails(), errors);
        validator.checkAvatar(formBean.getAvatar(), errors);

        if (!errors.isEmpty()) {
            userUtils.fillUserData(formBean, userData);
            httpServletRequest.setAttribute(USER_DATA, userData);
            httpServletRequest.setAttribute(ERRORS, errors);
            forward(httpServletRequest, httpServletResponse);
            return;
        }

        User user = userUtils.createUserFromBean(formBean);

        if (Objects.nonNull(userService.getByLogin(user.getLogin()))) {
            userUtils.checkLoginAndEmail(user, userService, errors);
            userUtils.fillUserData(formBean, userData);
            httpServletRequest.setAttribute(USER_DATA, userData);
            httpServletRequest.setAttribute(ERRORS, errors);
            forward(httpServletRequest, httpServletResponse);
            return;
        }

        String path = imageDraw.saveUploadedFile(formBean.getAvatar(), formBean.getLogin());
        formBean.setAvatarPath(path);

        if (Objects.isNull(userService.add(user))) {
            errors.put(NOT_USER_ERROR, NOT_USER_ERROR);
            userUtils.fillUserData(formBean, userData);
            httpServletRequest.setAttribute(USER_DATA, userData);
            httpServletRequest.setAttribute(ERRORS, errors);
            forward(httpServletRequest, httpServletResponse);
            return;
        }

        session.setAttribute(USER_LOGIN, formBean.getLogin());
        httpServletResponse.sendRedirect("/");
    }

    private void forward(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String key = String.valueOf(currentTimeMillis());
        httpServletRequest.getSession().setAttribute(CAPTCHA_KEY, key);
        httpServletRequest.getRequestDispatcher(REGISTRATION_JSP_LINK).forward(httpServletRequest, httpServletResponse);
    }
}
