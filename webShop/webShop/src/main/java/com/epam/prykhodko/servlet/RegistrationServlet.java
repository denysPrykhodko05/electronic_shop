package com.epam.prykhodko.servlet;


import com.epam.prykhodko.bean.RegFormBean;
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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getSession().removeAttribute("errors");
        httpServletRequest.getSession().removeAttribute("userData");
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
        httpServletRequest.getRequestDispatcher("jsp/registration.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
