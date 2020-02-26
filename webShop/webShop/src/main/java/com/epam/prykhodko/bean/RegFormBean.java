package com.epam.prykhodko.bean;

import static com.epam.prykhodko.constants.Constants.CO_PASSWORD;
import static com.epam.prykhodko.constants.Constants.EMAIL;
import static com.epam.prykhodko.constants.Constants.LOGIN;
import static com.epam.prykhodko.constants.Constants.MAILS;
import static com.epam.prykhodko.constants.Constants.NAME;
import static com.epam.prykhodko.constants.Constants.PASSWORD;
import static com.epam.prykhodko.constants.Constants.POLICY;
import static com.epam.prykhodko.constants.Constants.REG_CAPTCHA;
import static com.epam.prykhodko.constants.Constants.SURNAME;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegFormBean {

    private final HttpServletRequest httpServletRequest;
    private String name;
    private String surname;
    private String login;
    private String email;
    private String password;
    private String confirmPassword;
    private String policy;
    private String mails;
    private String captcha;

    public RegFormBean(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
        name = httpServletRequest.getParameter(NAME);
        surname = httpServletRequest.getParameter(SURNAME);
        login = httpServletRequest.getParameter(LOGIN);
        email = httpServletRequest.getParameter(EMAIL);
        password = httpServletRequest.getParameter(PASSWORD);
        confirmPassword = httpServletRequest.getParameter(CO_PASSWORD);
        policy = httpServletRequest.getParameter(POLICY);
        mails = httpServletRequest.getParameter(MAILS);
        captcha = httpServletRequest.getParameter(REG_CAPTCHA);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getPolicy() {
        return policy;
    }

    public String getMails() {
        return mails;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public String getCaptcha() {
        return captcha;
    }
}
