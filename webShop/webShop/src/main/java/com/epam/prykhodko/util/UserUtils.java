package com.epam.prykhodko.util;

import static com.epam.prykhodko.constants.Constants.EMAIL;
import static com.epam.prykhodko.constants.Constants.LOGIN;
import static com.epam.prykhodko.constants.Constants.NAME;
import static com.epam.prykhodko.constants.Constants.SURNAME;

import com.epam.prykhodko.bean.RegFormBean;
import com.epam.prykhodko.entity.User;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class UserUtils {

    private UserUtils(){

    }

    private static void fillUserData(RegFormBean formBean, Map<String, String> userData) {
        userData.put(NAME, formBean.getName());
        userData.put(SURNAME, formBean.getSurname());
        userData.put(LOGIN, formBean.getLogin());
        userData.put(EMAIL, formBean.getEmail());
    }

    public static User createUser(RegFormBean formBean, HttpServletRequest httpServletRequest) {
        ServletContext servletContext = httpServletRequest.getServletContext();
        List<User> users = (List<User>) servletContext.getAttribute("users");
        Map<String, String> userData = new LinkedHashMap<>();
        Map<String, String> errors = new LinkedHashMap<>();
        User newUser = new User(formBean.getName(), formBean.getSurname(), formBean.getEmail(), formBean.getLogin(), formBean.getPassword());
        if (users.contains(newUser)) {
            User user = users.get(users.indexOf(newUser));
            if (user.getLogin().equals(newUser.getLogin())) {
                errors.put("contains-login", "User with this login is exists");
            }
            if (user.getEmail().equals(newUser.getEmail())) {
                errors.put("contains-email", "User with this email is exists");
            }
            httpServletRequest.getSession().setAttribute("errors", errors);
            httpServletRequest.getSession().setAttribute("userData", userData);
            fillUserData(formBean, userData);
            return null;
        }
        users.add(newUser);
        return newUser;
    }
}
