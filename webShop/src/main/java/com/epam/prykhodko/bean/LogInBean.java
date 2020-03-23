package com.epam.prykhodko.bean;

import static com.epam.prykhodko.constants.ApplicationConstants.LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.PASSWORD;

import javax.servlet.http.HttpServletRequest;

public class LogInBean {

    private static String login;
    private static String password;

    public static LogInBean createLoginBeanFromRequest(HttpServletRequest httpServletRequest) {
        LogInBean logInBean = new LogInBean();
        logInBean.login = httpServletRequest.getParameter(LOGIN);
        logInBean.password = httpServletRequest.getParameter(PASSWORD);
        return logInBean;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
