package com.epam.prykhodko.servlet;

import static com.epam.prykhodko.constants.ApplicationConstants.LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.LOGIN_JSP_LINK;
import static com.epam.prykhodko.constants.ApplicationConstants.LOGIN_REGEX;
import static com.epam.prykhodko.constants.ApplicationConstants.PASSWORD;
import static com.epam.prykhodko.constants.ApplicationConstants.PASSWORD_REGEX;
import static com.epam.prykhodko.constants.ApplicationConstants.VALIDATOR;

import com.epam.prykhodko.bean.LogInBean;
import com.epam.prykhodko.util.Validator;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private Validator validator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        validator = (Validator) servletContext.getAttribute(VALIDATOR);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogInBean logInBean = new LogInBean();
        Map<String, String> errors = new LinkedHashMap<>();
        logInBean.setLoginForm(req);
        validator.checkField(LOGIN, logInBean.getLogin(), LOGIN_REGEX, errors);
        validator.checkField(PASSWORD,logInBean.getPassword(), PASSWORD_REGEX,errors);

    }

    private void forward(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher(LOGIN_JSP_LINK).forward(httpServletRequest, httpServletResponse);
    }
}
