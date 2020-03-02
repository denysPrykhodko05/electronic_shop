package com.epam.prykhodko.util;

import static com.epam.prykhodko.constants.Constants.EMAIL;
import static com.epam.prykhodko.constants.Constants.INCORRECT_CAPTCHA;
import static com.epam.prykhodko.constants.Constants.INCORRECT_INPUT;
import static com.epam.prykhodko.constants.Constants.LOGIN;
import static com.epam.prykhodko.constants.Constants.NAME;
import static com.epam.prykhodko.constants.Constants.REG_CAPTCHA;
import static com.epam.prykhodko.constants.Constants.SURNAME;
import static com.epam.prykhodko.constants.Constants.YOU_DONT_CHOOSE;

import com.epam.prykhodko.bean.RegFormBean;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;

public class Validator {


    public void checkField(String parameter, String data, String regex, Map<String, String> errors) {
        if (!data.matches(regex)) {
            errors.put(parameter, INCORRECT_INPUT + parameter);
        }
    }

    public void checkCheckbox(String parameter, String value, Map<String, String> errors) {
        if (Objects.isNull(value)) {
            errors.put(parameter, YOU_DONT_CHOOSE + parameter);
        }
    }

    public void fillUserData(RegFormBean formBean, Map<String, String> userData) {
        userData.put(NAME, formBean.getName());
        userData.put(SURNAME, formBean.getSurname());
        userData.put(LOGIN, formBean.getLogin());
        userData.put(EMAIL, formBean.getEmail());
    }

    public boolean checkCaptcha(Map<Long, String> captchaKeys, Long userKey, String captchaValue, Map<String, String> errors) {
        Optional<Entry<Long, String>> key = captchaKeys.entrySet().stream()
            .filter(e -> e.getKey().equals(userKey)
                && e.getValue().equals(captchaValue))
            .findFirst();

        if (key.isPresent()) {
            captchaKeys.remove(key.get().getKey());
            return true;
        }
        errors.put(REG_CAPTCHA, INCORRECT_CAPTCHA);
        captchaKeys.remove(userKey);
        return false;
    }
}
