package com.epam.prykhodko.util;

import static com.epam.prykhodko.constants.Constants.EMAIL;
import static com.epam.prykhodko.constants.Constants.LOGIN;
import static com.epam.prykhodko.constants.Constants.NAME;
import static com.epam.prykhodko.constants.Constants.SURNAME;
import static com.epam.prykhodko.constants.Constants.USER_EMAIL_EXISTS;
import static com.epam.prykhodko.constants.Constants.USER_LOGIN_EXISTS;

import com.epam.prykhodko.bean.RegFormBean;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.service.UserService;
import java.util.Map;
import java.util.Objects;

public class UserUtils {

    public void fillUserData(RegFormBean formBean, Map<String, String> userData) {
        userData.put(NAME, formBean.getName());
        userData.put(SURNAME, formBean.getSurname());
        userData.put(LOGIN, formBean.getLogin());
        userData.put(EMAIL, formBean.getEmail());
    }

    public void checkLoginAndEmail(User user, UserService userService, Map<String, String> errors) {
        if (Objects.nonNull(userService.getByLogin(user.getLogin()))) {
            errors.put(LOGIN, USER_LOGIN_EXISTS);
            return;
        }
        errors.put(EMAIL, USER_EMAIL_EXISTS);
    }
}
