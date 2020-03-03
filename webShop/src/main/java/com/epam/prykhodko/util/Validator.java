package com.epam.prykhodko.util;

import static com.epam.prykhodko.constants.Constants.INCORRECT_CAPTCHA;
import static com.epam.prykhodko.constants.Constants.INCORRECT_INPUT;
import static com.epam.prykhodko.constants.Constants.REG_CAPTCHA;
import static com.epam.prykhodko.constants.Constants.YOU_DONT_CHOOSE;

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
