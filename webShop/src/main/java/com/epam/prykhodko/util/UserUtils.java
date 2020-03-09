package com.epam.prykhodko.util;

import static com.epam.prykhodko.constants.ApplicationConstants.EMAIL;
import static com.epam.prykhodko.constants.ApplicationConstants.LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.NAME;
import static com.epam.prykhodko.constants.ApplicationConstants.SURNAME;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_EMAIL_EXISTS;
import static com.epam.prykhodko.constants.ApplicationConstants.USER_LOGIN_EXISTS;

import com.epam.prykhodko.bean.RegFormBean;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.service.DAOService;
import java.util.Map;
import java.util.Objects;

public class UserUtils {

    public void fillUserData(RegFormBean formBean, Map<String, String> userData) {
        userData.put(NAME, formBean.getName());
        userData.put(SURNAME, formBean.getSurname());
        userData.put(LOGIN, formBean.getLogin());
        userData.put(EMAIL, formBean.getEmail());
    }

    public void checkLoginAndEmail(User user, DAOService DAOService, Map<String, String> errors) {
        if (Objects.nonNull(DAOService.getByLogin(user.getLogin()))) {
            errors.put(LOGIN, USER_LOGIN_EXISTS);
            return;
        }
        errors.put(EMAIL, USER_EMAIL_EXISTS);
    }
}
