package com.nure.prykhodko.util;

import com.nure.prykhodko.constants.ApplicationConstants;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.fileupload.FileItem;

public class Validator {


    public void checkField(String parameter, String data, String regex, Map<String, String> errors) {
        if (Objects.isNull(data) || !data.matches(regex)) {
            errors.put(parameter, ApplicationConstants.INCORRECT_INPUT + parameter);
        }
    }

    public void checkCheckbox(String parameter, String value, Map<String, String> errors) {
        if (Objects.isNull(value)) {
            errors.put(parameter, ApplicationConstants.YOU_DONT_CHOOSE + parameter);
        }
    }

    public void checkAvatar(FileItem fileItem, Map<String, String> errors) {
        if (Objects.isNull(fileItem)) {
            errors.put(ApplicationConstants.AVATAR, ApplicationConstants.YOU_DONT_CHOOSE + ApplicationConstants.AVATAR);
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
        errors.put(ApplicationConstants.REG_CAPTCHA, ApplicationConstants.INCORRECT_CAPTCHA);
        captchaKeys.remove(userKey);
        return false;
    }
}
