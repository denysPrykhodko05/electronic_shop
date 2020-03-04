package com.epam.prykhodko.bean;

import static com.epam.prykhodko.constants.ApplicationConstants.CO_PASSWORD;
import static com.epam.prykhodko.constants.ApplicationConstants.EMAIL;
import static com.epam.prykhodko.constants.ApplicationConstants.LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.MAILS;
import static com.epam.prykhodko.constants.ApplicationConstants.NAME;
import static com.epam.prykhodko.constants.ApplicationConstants.PASSWORD;
import static com.epam.prykhodko.constants.ApplicationConstants.POLICY;
import static com.epam.prykhodko.constants.ApplicationConstants.REG_CAPTCHA;
import static com.epam.prykhodko.constants.ApplicationConstants.SURNAME;

import javax.servlet.http.HttpServletRequest;

public class RegFormBean {

    private String name;
    private String surname;
    private String login;
    private String email;
    private String password;
    private String confirmPassword;
    private String policy;
    private String mails;
    private String captcha;

    public void setRegFormBean(HttpServletRequest httpServletRequest){
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

    public String getCaptcha() {
        return captcha;
    }
}
