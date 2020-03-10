package com.epam.prykhodko.bean;

import static com.epam.prykhodko.constants.ApplicationConstants.LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.PASSWORD;

import javax.servlet.http.HttpServletRequest;

public class LogInBean {

    private String login;
    private String password;

    public void setLoginForm(HttpServletRequest httpServletRequest) {
        login = httpServletRequest.getParameter(LOGIN);
        password = httpServletRequest.getParameter(PASSWORD);
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
