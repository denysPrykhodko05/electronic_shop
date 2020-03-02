package com.epam.prykhodko.servlet;


import static com.epam.prykhodko.constants.Constants.ERRORS;
import static com.epam.prykhodko.constants.Constants.REGISTRATION_JSP_LINK;
import static com.epam.prykhodko.constants.Constants.USER_DATA;

import com.epam.prykhodko.bean.RegFormBean;
import com.epam.prykhodko.dao.impl.UserDAO;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.util.UserUtils;
import com.epam.prykhodko.util.Validator;
import java.io.IOException;
import java.util.Objects;
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
        UserDAO userDAO = new UserDAO();
        userDAO.add(new User());
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute(ERRORS);
        session.removeAttribute(USER_DATA);
        redirect(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        RegFormBean formBean = new RegFormBean(httpServletRequest);
        if (!Validator.regFormIsValid(formBean)) {
            redirect(httpServletRequest, httpServletResponse);
            return;
        }
        User user = UserUtils.createUser(formBean, httpServletRequest);
        if (Objects.isNull(user)) {
            redirect(httpServletRequest, httpServletResponse);
            return;
        }
        httpServletResponse.sendRedirect("/");
    }

    private void redirect(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher(REGISTRATION_JSP_LINK).forward(httpServletRequest, httpServletResponse);
    }
}
