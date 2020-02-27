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
import static com.epam.prykhodko.constants.Constants.REG_CAPTCHA;
import static com.epam.prykhodko.constants.Constants.SURNAME;
import static com.epam.prykhodko.constants.Constants.USER_PERSONAL_DATA_REGEX;

import com.epam.prykhodko.bean.RegFormBean;
import com.epam.prykhodko.captcha_keepers.CaptchaKeeper;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public abstract class Validator {

    @SuppressWarnings("unchecked")
    public static boolean regFormIsValid(RegFormBean formBean) {
        Map<String, String> errors = new LinkedHashMap<>();
        Map<String, String> userData = new LinkedHashMap<>();
        HttpSession session = formBean.getHttpServletRequest().getSession();
        ServletContext servletContext = formBean.getHttpServletRequest().getServletContext();
        Map<String, String> captchaKeys = (Map<String, String>) servletContext.getAttribute("captchaKeys");
        String keeper = servletContext.getInitParameter("captcha");
        Map<String, CaptchaKeeper> keepers = (Map<String, CaptchaKeeper>) servletContext.getAttribute("keepers");
        CaptchaKeeper captchaKeeper = keepers.get(keeper);
        checkTimer(session, errors);
        checkField(NAME, formBean.getName(), USER_PERSONAL_DATA_REGEX, errors);
        checkField(SURNAME, formBean.getSurname(), USER_PERSONAL_DATA_REGEX, errors);
        checkField(LOGIN, formBean.getLogin(), LOGIN_REGEX, errors);
        checkField(EMAIL, formBean.getEmail(), EMAIL_REGEX, errors);
        checkField(PASSWORD, formBean.getPassword(), PASSWORD_REGEX, errors);
        checkField(CO_PASSWORD, formBean.getPassword(), formBean.getConfirmPassword(), errors);
        checkCaptcha(captchaKeys, captchaKeeper.get(formBean.getHttpServletRequest()), formBean.getCaptcha(), errors);
        checkCheckbox(POLICY, formBean.getPolicy(), errors);
        checkCheckbox(MAILS, formBean.getMails(), errors);
        if (!errors.isEmpty()) {
            fillUserData(formBean, userData);
            session.setAttribute("errors", errors);
            session.setAttribute("userData", userData);
            return false;
        }
        return true;
    }

    private static void checkTimer(HttpSession session, Map<String, String> errors) {
        if (!(boolean) session.getAttribute("timer")) {
            errors.put("Timer", "Times up");
        }
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

    private static void fillUserData(RegFormBean formBean, Map<String, String> userData) {
        userData.put(NAME, formBean.getName());
        userData.put(SURNAME, formBean.getSurname());
        userData.put(LOGIN, formBean.getLogin());
        userData.put(EMAIL, formBean.getEmail());
    }

    private static boolean checkCaptcha(Map<String, String> captchaKeys, String userKey, String captchaValue, Map<String, String> errors) {
        Optional<Entry<String, String>> key = captchaKeys.entrySet().stream()
            .filter(e -> e.getKey().equals(userKey)
                && e.getValue().equals(captchaValue))
            .findFirst();

        if (key.isPresent()) {
            captchaKeys.remove(key.get().getKey());
            return true;
        }
        errors.put(REG_CAPTCHA, "Incorrect captcha");
        captchaKeys.remove(userKey);
        return false;
    }
}
