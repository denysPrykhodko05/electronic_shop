package com.epam.prykhodko.bean;

import javax.servlet.http.HttpServletRequest;

public class LogInBean {

    private String login;
    private String password;

    public void setLoginForm(HttpServletRequest httpServletRequest) {
        login = httpServletRequest.getParameter("login");
        password = httpServletRequest.getParameter("password");
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
