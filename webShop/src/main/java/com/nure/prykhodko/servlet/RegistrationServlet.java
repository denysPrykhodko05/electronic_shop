package com.nure.prykhodko.servlet;


import static java.lang.System.currentTimeMillis;

import com.nure.prykhodko.bean.RegFormBean;
import com.nure.prykhodko.captchakeepers.CaptchaKeeper;
import com.nure.prykhodko.entity.User;
import com.nure.prykhodko.service.UserService;
import com.nure.prykhodko.util.ImageDraw;
import com.nure.prykhodko.util.UserUtils;
import com.nure.prykhodko.util.Validator;
import com.nure.prykhodko.constants.ApplicationConstants;
import com.nure.prykhodko.constants.LoggerMessagesConstants;
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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);
    private static final String FILE = "FILE";
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute(ApplicationConstants.ERRORS);
        session.removeAttribute(ApplicationConstants.USER_DATA);
        forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Map<String, String> errors = new LinkedHashMap<>();
        Map<String, String> userData = new LinkedHashMap<>();
        ServletContext servletContext = httpServletRequest.getServletContext();
        HttpSession session = httpServletRequest.getSession();
        RegFormBean formBean = null;
        userService = (UserService) servletContext.getAttribute(ApplicationConstants.USER_SERVICE);
        UserUtils userUtils = (UserUtils) servletContext.getAttribute(ApplicationConstants.USER_UTILS);
        Validator validator = (Validator) servletContext.getAttribute(ApplicationConstants.VALIDATOR);
        ImageDraw imageDraw = (ImageDraw) servletContext.getAttribute(ApplicationConstants.IMAGE_DRAW);
        CaptchaKeeper captchaKeeper = (CaptchaKeeper) servletContext.getAttribute(ApplicationConstants.CAPTCHA_KEEPER);
        Map<Long, String> captchaKeys = (Map<Long, String>) servletContext.getAttribute(ApplicationConstants.CAPTCHA_KEYS);

        try {
            formBean = RegFormBean.fromRequestToRegFormBean(httpServletRequest);
        } catch (FileUploadException e) {
            errors.put(FILE, LoggerMessagesConstants.ERR_CANNOT_LOAD_FILE);
            LOGGER.error(LoggerMessagesConstants.ERR_CANNOT_LOAD_FILE);
            forward(httpServletRequest, httpServletResponse);
            return;
        }

        validator.checkField(ApplicationConstants.NAME, formBean.getName(), ApplicationConstants.USER_PERSONAL_DATA_REGEX, errors);
        validator.checkField(ApplicationConstants.SURNAME, formBean.getSurname(), ApplicationConstants.USER_PERSONAL_DATA_REGEX, errors);
        validator.checkField(ApplicationConstants.LOGIN, formBean.getLogin(), ApplicationConstants.LOGIN_REGEX, errors);
        validator.checkField(ApplicationConstants.EMAIL, formBean.getEmail(), ApplicationConstants.EMAIL_REGEX, errors);
        validator.checkField(ApplicationConstants.PASSWORD, formBean.getPassword(), ApplicationConstants.PASSWORD_REGEX, errors);
        validator.checkField(ApplicationConstants.CO_PASSWORD, formBean.getPassword(), formBean.getConfirmPassword(), errors);
        validator.checkCaptcha(captchaKeys, captchaKeeper.get(httpServletRequest), formBean.getCaptcha(), errors);
        validator.checkCheckbox(ApplicationConstants.POLICY, formBean.getPolicy(), errors);
        validator.checkCheckbox(ApplicationConstants.MAILS, formBean.getMails(), errors);
        validator.checkAvatar(formBean.getAvatar(), errors);

        if (!errors.isEmpty()) {
            userUtils.fillUserData(formBean, userData);
            httpServletRequest.setAttribute(ApplicationConstants.USER_DATA, userData);
            httpServletRequest.setAttribute(ApplicationConstants.ERRORS, errors);
            forward(httpServletRequest, httpServletResponse);
            return;
        }

        User user = userUtils.createUserFromBean(formBean);

        if (Objects.nonNull(userService.getUser(user.getLogin()))) {
            userUtils.checkLoginAndEmail(user, userService, errors);
            userUtils.fillUserData(formBean, userData);
            httpServletRequest.setAttribute(ApplicationConstants.USER_DATA, userData);
            httpServletRequest.setAttribute(ApplicationConstants.ERRORS, errors);
            forward(httpServletRequest, httpServletResponse);
            return;
        }

        String path = imageDraw.saveUploadedFile(formBean.getAvatar(), formBean.getLogin());
        formBean.setAvatarPath(path);

        if (Objects.isNull(userService.add(user))) {
            errors.put(ApplicationConstants.NOT_USER_ERROR, ApplicationConstants.NOT_USER_ERROR);
            userUtils.fillUserData(formBean, userData);
            httpServletRequest.setAttribute(ApplicationConstants.USER_DATA, userData);
            httpServletRequest.setAttribute(ApplicationConstants.ERRORS, errors);
            forward(httpServletRequest, httpServletResponse);
            return;
        }

        session.setAttribute(ApplicationConstants.USER_LOGIN, formBean.getLogin());
        httpServletResponse.sendRedirect("/");
    }

    private void forward(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String key = String.valueOf(currentTimeMillis());
        httpServletRequest.getSession().setAttribute(ApplicationConstants.CAPTCHA_KEY, key);
        httpServletRequest.getRequestDispatcher(ApplicationConstants.REGISTRATION_JSP_LINK).forward(httpServletRequest, httpServletResponse);
    }
}
