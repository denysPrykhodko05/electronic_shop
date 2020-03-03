package com.epam.prykhodko.servlet;


import static com.epam.prykhodko.constants.Constants.CAPTCHA_KEEPER;
import static com.epam.prykhodko.constants.Constants.CAPTCHA_KEY;
import static com.epam.prykhodko.constants.Constants.CAPTCHA_KEYS;
import static com.epam.prykhodko.constants.Constants.CO_PASSWORD;
import static com.epam.prykhodko.constants.Constants.EMAIL;
import static com.epam.prykhodko.constants.Constants.EMAIL_REGEX;
import static com.epam.prykhodko.constants.Constants.ERRORS;
import static com.epam.prykhodko.constants.Constants.LOGIN;
import static com.epam.prykhodko.constants.Constants.LOGIN_REGEX;
import static com.epam.prykhodko.constants.Constants.MAILS;
import static com.epam.prykhodko.constants.Constants.NAME;
import static com.epam.prykhodko.constants.Constants.PASSWORD;
import static com.epam.prykhodko.constants.Constants.PASSWORD_REGEX;
import static com.epam.prykhodko.constants.Constants.POLICY;
import static com.epam.prykhodko.constants.Constants.REGISTRATION_JSP_LINK;
import static com.epam.prykhodko.constants.Constants.REG_FORM;
import static com.epam.prykhodko.constants.Constants.SURNAME;
import static com.epam.prykhodko.constants.Constants.USER_DATA;
import static com.epam.prykhodko.constants.Constants.USER_PERSONAL_DATA_REGEX;
import static com.epam.prykhodko.constants.Constants.USER_SERVICE;
import static com.epam.prykhodko.constants.Constants.USER_UTILS;
import static com.epam.prykhodko.constants.Constants.VALIDATOR;
import static java.lang.System.currentTimeMillis;

import com.epam.prykhodko.bean.RegFormBean;
import com.epam.prykhodko.captchakeepers.CaptchaKeeper;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.service.UserService;
import com.epam.prykhodko.util.UserUtils;
import com.epam.prykhodko.util.Validator;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        String key = String.valueOf(currentTimeMillis());
        session.setAttribute(CAPTCHA_KEY, key);
        session.removeAttribute(ERRORS);
        session.removeAttribute(USER_DATA);
        forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Map<String, String> errors = new LinkedHashMap<>();
        Map<String, String> userData = new LinkedHashMap<>();
        ServletContext servletContext = httpServletRequest.getServletContext();
        RegFormBean formBean = (RegFormBean) servletContext.getAttribute(REG_FORM);
        UserService userService = (UserService) servletContext.getAttribute(USER_SERVICE);
        UserUtils userUtils = (UserUtils) servletContext.getAttribute(USER_UTILS);
        Validator validator = (Validator) servletContext.getAttribute(VALIDATOR);
        CaptchaKeeper captchaKeeper = (CaptchaKeeper) servletContext.getAttribute(CAPTCHA_KEEPER);
        Map<Long, String> captchaKeys = (Map<Long, String>) servletContext.getAttribute(CAPTCHA_KEYS);
        formBean.setRegFormBean(httpServletRequest);
        validator.checkField(NAME, formBean.getName(), USER_PERSONAL_DATA_REGEX, errors);
        validator.checkField(SURNAME, formBean.getSurname(), USER_PERSONAL_DATA_REGEX, errors);
        validator.checkField(LOGIN, formBean.getLogin(), LOGIN_REGEX, errors);
        validator.checkField(EMAIL, formBean.getEmail(), EMAIL_REGEX, errors);
        validator.checkField(PASSWORD, formBean.getPassword(), PASSWORD_REGEX, errors);
        validator.checkField(CO_PASSWORD, formBean.getPassword(), formBean.getConfirmPassword(), errors);
        validator.checkCaptcha(captchaKeys, captchaKeeper.get(httpServletRequest), formBean.getCaptcha(), errors);
        validator.checkCheckbox(POLICY, formBean.getPolicy(), errors);
        validator.checkCheckbox(MAILS, formBean.getMails(), errors);
        if (!errors.isEmpty()) {
            userUtils.fillUserData(formBean, userData);
            httpServletRequest.setAttribute(USER_DATA, userData);
            httpServletRequest.setAttribute(ERRORS, errors);
            forward(httpServletRequest, httpServletResponse);
            return;
        }
        User user = userService.createUser(formBean);
        if (userService.isContains(user)) {
            userUtils.checkLoginAndEmail(user, userService, errors);
            userUtils.fillUserData(formBean, userData);
            httpServletRequest.setAttribute(USER_DATA, userData);
            httpServletRequest.setAttribute(ERRORS, errors);
            forward(httpServletRequest, httpServletResponse);
            return;
        }
        userService.add(formBean);
        httpServletResponse.sendRedirect("/");
    }

    private void forward(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher(REGISTRATION_JSP_LINK).forward(httpServletRequest, httpServletResponse);
    }
}
