package com.epam.prykhodko.util;

import static com.epam.prykhodko.constants.Constants.CO_PASSWORD;
import static com.epam.prykhodko.constants.Constants.EMAIL;
import static com.epam.prykhodko.constants.Constants.EMAIL_REGEX;
import static com.epam.prykhodko.constants.Constants.LOGIN;
import static com.epam.prykhodko.constants.Constants.LOGIN_REGEX;
import static com.epam.prykhodko.constants.Constants.MAILS;
import static com.epam.prykhodko.constants.Constants.NAME;
import static com.epam.prykhodko.constants.Constants.PASSWORD;
import static com.epam.prykhodko.constants.Constants.PASSWORD_REGEX;
import static com.epam.prykhodko.constants.Constants.POLICY;
import static com.epam.prykhodko.constants.Constants.SURNAME;
import static com.epam.prykhodko.constants.Constants.USER_PERSONAL_DATA_REGEX;

import com.epam.prykhodko.bean.FormBean;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpSession;

public abstract class Validator {


    public static boolean formIsValid(FormBean formBean) {
        Map<String, String> errors = new LinkedHashMap<>();
        Map<String, String> userData = new LinkedHashMap<>();
        checkField(NAME, formBean.getName(), USER_PERSONAL_DATA_REGEX, errors);
        checkField(SURNAME, formBean.getSurname(), USER_PERSONAL_DATA_REGEX, errors);
        checkField(LOGIN, formBean.getLogin(), LOGIN_REGEX, errors);
        checkField(PASSWORD, formBean.getPassword(), PASSWORD_REGEX, errors);
        if (!formBean.getPassword().equals(formBean.getConfirmPassword())) {
            errors.put(CO_PASSWORD, "Incorrect confirm password");
        }
        checkField(EMAIL, formBean.getEmail(), EMAIL_REGEX, errors);
        checkCheckbox(POLICY, formBean.getPolicy(), errors);
        checkCheckbox(MAILS, formBean.getMails(), errors);
        if (!errors.isEmpty()) {
            fillUserData(formBean, userData);
            HttpSession session = formBean.getHttpServletRequest().getSession();
            session.setAttribute("errors", errors);
            session.setAttribute("userData", userData);
            return false;
        }
        return true;
    }

    private static void checkField(String parameter, String data, String regex, Map<String, String> errors) {
        if (!data.matches(regex)) {
            errors.put(parameter, "Incorrect input of " + parameter);
        }
    }

    private static void checkCheckbox(String parameter, String value, Map<String, String> errors) {
        if (Objects.isNull(value)) {
            errors.put(parameter, "You don't choose " + parameter);
        }
    }

    private static void fillUserData(FormBean formBean, Map<String, String> userData) {
        userData.put(NAME, formBean.getName());
        userData.put(SURNAME, formBean.getSurname());
        userData.put(LOGIN, formBean.getLogin());
        userData.put(EMAIL, formBean.getEmail());
    }
}
