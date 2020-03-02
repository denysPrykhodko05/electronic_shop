package com.epam.prykhodko.util;

import static com.epam.prykhodko.constants.Constants.CONTAINS_EMAIL;
import static com.epam.prykhodko.constants.Constants.CONTAINS_LOGIN;
import static com.epam.prykhodko.constants.Constants.EMAIL;
import static com.epam.prykhodko.constants.Constants.ERRORS;
import static com.epam.prykhodko.constants.Constants.LOGIN;
import static com.epam.prykhodko.constants.Constants.NAME;
import static com.epam.prykhodko.constants.Constants.SURNAME;
import static com.epam.prykhodko.constants.Constants.USERS;
import static com.epam.prykhodko.constants.Constants.USER_DATA;
import static com.epam.prykhodko.constants.Constants.USER_EMAIL_EXISTS;
import static com.epam.prykhodko.constants.Constants.USER_LOGIN_EXISTS;

import com.epam.prykhodko.bean.RegFormBean;
import com.epam.prykhodko.entity.User;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("unchecked")
public class UserUtils {

    private UserUtils() {

    }

    private static void fillUserData(RegFormBean formBean, Map<String, String> userData) {
        userData.put(NAME, formBean.getName());
        userData.put(SURNAME, formBean.getSurname());
        userData.put(LOGIN, formBean.getLogin());
        userData.put(EMAIL, formBean.getEmail());
    }

    public static User createUser(RegFormBean formBean, HttpServletRequest httpServletRequest) {
        ServletContext servletContext = httpServletRequest.getServletContext();
        List<User> users = (List<User>) servletContext.getAttribute(USERS);
        Map<String, String> userData = new LinkedHashMap<>();
        Map<String, String> errors = new LinkedHashMap<>();
        User newUser = new User(formBean.getName(), formBean.getSurname(), formBean.getEmail(), formBean.getLogin(), formBean.getPassword());
        if (users.contains(newUser)) {
            User user = users.get(users.indexOf(newUser));
            if (user.getLogin().equals(newUser.getLogin())) {
                errors.put(CONTAINS_LOGIN, USER_LOGIN_EXISTS);
            }
            if (user.getEmail().equals(newUser.getEmail())) {
                errors.put(CONTAINS_EMAIL, USER_EMAIL_EXISTS);
            }
            httpServletRequest.getSession().setAttribute(ERRORS, errors);
            httpServletRequest.getSession().setAttribute(USER_DATA, userData);
            fillUserData(formBean, userData);
            return null;
        }
        users.add(newUser);
        return newUser;
    }
}
