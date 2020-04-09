package com.nure.prykhodko.bean;

import com.nure.prykhodko.constants.ApplicationConstants;
import javax.servlet.http.HttpServletRequest;

public class LogInBean {

    private static String login;
    private static String password;

    public static LogInBean createLoginBeanFromRequest(HttpServletRequest httpServletRequest) {
        LogInBean logInBean = new LogInBean();
        logInBean.login = httpServletRequest.getParameter(ApplicationConstants.LOGIN);
        logInBean.password = httpServletRequest.getParameter(ApplicationConstants.PASSWORD);
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
