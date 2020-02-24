package com.epam.prykhodko.servlet;


import static com.epam.prykhodko.constants.Constants.EMAIL;
import static com.epam.prykhodko.constants.Constants.LOGIN;
import static com.epam.prykhodko.constants.Constants.MAILS;
import static com.epam.prykhodko.constants.Constants.NAME;
import static com.epam.prykhodko.constants.Constants.PASSWORD;
import static com.epam.prykhodko.constants.Constants.POLICY;
import static com.epam.prykhodko.constants.Constants.SURNAME;

import com.epam.prykhodko.constants.Constants;
import com.epam.prykhodko.entity.CaptchaImage;
import com.epam.prykhodko.entity.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private Map<String, String> errors = new LinkedHashMap<>();
    private Map<String, String> userData = new LinkedHashMap<>();

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        CaptchaImage obj = new CaptchaImage();
        BufferedImage ima = obj.getCaptchaImage();
        File outputfile = new File("src/main/webapp/images/captcha.jpg");
        ImageIO.write(ima, "jpg", outputfile);
        String captchaStr = obj.getCaptchaString();

        session.setAttribute("captchaStr", captchaStr);

        if (!errors.isEmpty()) {
            httpServletRequest.setAttribute("userData", userData);
            httpServletRequest.setAttribute("errors", errors);
            errors = new LinkedHashMap<>();
            userData = new LinkedHashMap<>();
        }

        httpServletRequest.getRequestDispatcher("jsp/registration.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String name = httpServletRequest.getParameter("name");
        String surname = httpServletRequest.getParameter("surname");
        String login = httpServletRequest.getParameter("login");
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        String co_password = httpServletRequest.getParameter("confirmPassword");
        String policy = httpServletRequest.getParameter("privacy-policy");
        String mails = httpServletRequest.getParameter("mails");
        ServletContext servletContext = httpServletRequest.getServletContext();
        List<User> users = (List<User>) servletContext.getAttribute("users");
        User user = createUser(name, surname, login, email, password,co_password, policy, users);
        if (Objects.isNull(user)) {
            fillUserData(name,surname,login,email);
            doGet(httpServletRequest,httpServletResponse);
            return;
        }
        httpServletResponse.sendRedirect("/");
    }

    private User createUser(String name, String surname, String login, String email, String password ,String co_password, String policy, List<User> users) {
        checkField(NAME, name, Constants.USER_PERSONAL_DATA_REGEX);
        checkField(SURNAME, surname, Constants.USER_PERSONAL_DATA_REGEX);
        checkField(LOGIN, login, Constants.LOGIN_REGEX);
        checkField(EMAIL, email, Constants.EMAIL_REGEX);
        checkField(PASSWORD, password, Constants.PASSWORD_REGEX);
        if (!password.equals(co_password)){
            errors.put("co_password", "Incorrect confirm of password");
        }
        checkCheckbox(POLICY, policy);
        if (!errors.isEmpty()) {
            return null;
        }
        User newUser = new User(name, surname, email, login, password);
        if (users.contains(newUser)) {
            User user = users.get(users.indexOf(newUser));
            if (user.getLogin().equals(newUser.getLogin())) {
                errors.put("contains-login", "User with this login is exists");
            }
            if (user.getEmail().equals(newUser.getEmail())) {
                errors.put("contains-email", "User with this email is exists");
            }
            return null;
        }
        users.add(newUser);
        return newUser;
    }

    private void checkField(String parameter, String data, String regex) {
        if (!data.matches(regex)) {
            errors.put(parameter, "Incorrect input of " + parameter);
        }
    }

    private void checkCheckbox(String parameter, String value) {
        if (Objects.isNull(value)) {
            errors.put(parameter, "You don't choose " + parameter);
        }
    }

    private void fillUserData(String name, String surname, String login, String email){
        userData.put("name", name);
        userData.put("surname", surname);
        userData.put("login", login);
        userData.put("email", email);
    }
}
