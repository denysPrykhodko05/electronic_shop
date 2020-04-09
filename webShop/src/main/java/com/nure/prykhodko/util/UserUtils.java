package com.nure.prykhodko.util;

import static com.nure.prykhodko.constants.ApplicationConstants.EMAIL;
import static com.nure.prykhodko.constants.ApplicationConstants.LOGIN;
import static com.nure.prykhodko.constants.ApplicationConstants.NAME;
import static com.nure.prykhodko.constants.ApplicationConstants.SURNAME;
import static com.nure.prykhodko.constants.ApplicationConstants.USER_EMAIL_EXISTS;
import static com.nure.prykhodko.constants.ApplicationConstants.USER_LOGIN_EXISTS;

import com.nure.prykhodko.bean.RegFormBean;
import com.nure.prykhodko.entity.User;
import com.nure.prykhodko.service.UserService;
import java.util.Base64;
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
        if (Objects.nonNull(userService.getUser(user.getLogin()))) {
            errors.put(LOGIN, USER_LOGIN_EXISTS);
            return;
        }
        errors.put(EMAIL, USER_EMAIL_EXISTS);
    }

    public User createUserFromBean(RegFormBean regFormBean) {
        String password = Base64.getEncoder().encodeToString(regFormBean.getPassword().getBytes());
        return new User(regFormBean.getName(), regFormBean.getSurname(), regFormBean.getEmail(), regFormBean.getLogin(), password);
    }
}
