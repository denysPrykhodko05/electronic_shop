package com.epam.prykhodko.servlet;

import static com.epam.prykhodko.constants.ApplicationConstants.ERRORS;
import static com.epam.prykhodko.constants.ApplicationConstants.HOME_URL;
import static com.epam.prykhodko.constants.ApplicationConstants.INCORRECT_INPUT;
import static com.epam.prykhodko.constants.ApplicationConstants.LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.LOGIN_JSP_LINK;
import static com.epam.prykhodko.constants.ApplicationConstants.LOGIN_REGEX;
import static com.epam.prykhodko.constants.ApplicationConstants.PASSWORD;
import static com.epam.prykhodko.constants.ApplicationConstants.PASSWORD_REGEX;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.VALIDATOR;

import com.epam.prykhodko.bean.LogInBean;
import com.epam.prykhodko.dao.DAO;
import com.epam.prykhodko.dao.impl.UserDAO;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.service.DAOService;
import com.epam.prykhodko.service.userservicedaoimpl.UserServiceDAOImpl;
import com.epam.prykhodko.util.Validator;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private Validator validator;
    private DAOService DAOService;
    private DAO<User> userRepository;

    @Override
    public void init(ServletConfig config) {
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
        userRepository = new UserDAO();
        DAOService = new UserServiceDAOImpl(userRepository);
        logInBean.setLoginForm(req);
        validator.checkField(LOGIN, logInBean.getLogin(), LOGIN_REGEX, errors);
        validator.checkField(PASSWORD, logInBean.getPassword(), PASSWORD_REGEX, errors);
        if (!errors.isEmpty()) {
            req.setAttribute(LOGIN, logInBean.getLogin());
            req.setAttribute(ERRORS, errors);
            forward(req, resp);
            return;
        }
        User user = new User();
        user.setLogin(logInBean.getLogin());
        user.setPassword(logInBean.getPassword());
        if (Objects.isNull(DAOService.getByName(user.getLogin()))) {
            errors.put(LOGIN, INCORRECT_INPUT + LOGIN);
            errors.put(PASSWORD, INCORRECT_INPUT + PASSWORD);
            req.setAttribute(LOGIN, logInBean.getLogin());
            req.setAttribute(ERRORS, errors);
            forward(req, resp);
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute(USER_LOGIN, user.getLogin());
        resp.sendRedirect(HOME_URL);
    }

    private void forward(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher(LOGIN_JSP_LINK).forward(httpServletRequest, httpServletResponse);
    }
}
