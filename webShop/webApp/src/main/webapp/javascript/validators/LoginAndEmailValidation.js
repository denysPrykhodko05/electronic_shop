$(document).ready(function() {

    realTimeValidation(LOGIN_ID, REGEX_LOGIN);
    realTimeValidation(EMAIL_ID, REGEX_EMAIL);

    $(FORM_ID).submit(function() {
        var email = $(EMAIL_ID).length;
        if (dataValidationOnSubmit($(LOGIN_ID), REGEX_LOGIN) && email > 0) {
            dataValidationOnSubmit($(EMAIL_ID), REGEX_EMAIL);
        };
        if ($(PRIVACY_POLICY_ID).length != 0) {
            policyValidation($(PRIVACY_POLICY_ID).prop(CHECKED_STRING));
        }
        submit($(FORM_ID));
    });

    function realTimeValidation(data, regex) {
        $(data).on(INPUT_STRING, function() {
            var form = $(FORM_ID);
            var input = $(data);
            var email = input.val();
            addValidationClass(email, regex, input, form);
        })
    }

    function addValidationClass(input, REGEX, element, form) {
        if (input.match(REGEX)) {
            element.removeClass(INVALID_STRING).addClass(VALID_STRING);
            form.removeClass(FORM_INVALID_STRING);
        } else {
            element.removeClass(VALID_STRING).addClass(INVALID_STRING);
            form.addClass(FORM_INVALID_STRING);
        }
    }

    function dataValidationOnSubmit(data, regex) {
        var value = data.val();
        if (!value.match(regex) || $(FORM_ID).hasClass(FORM_INVALID_STRING)) {
            $(FORM_ID).addClass(FORM_INVALID_STRING);
            if (data.hasClass(INVALID_STRING)) {
                alert(INCORRECT_LOGIN + " or " + INCORRECT_EMAIL);
                return false;
            }
        }
        $(FORM_ID).removeClass(FORM_INVALID_STRING);
        return true;
    }

    function policyValidation(policy) {
        if ($(FORM_ID).hasClass(FORM_INVALID_STRING)) {
            return;
        }

        if (!policy) {
            alert(AGREE_WITH_PRIVACY_POLICY_STRING);
            $(FORM_ID).addClass(FORM_INVALID_STRING);
            return;
        }
        $(FORM_ID).removeClass(FORM_INVALID_STRING);
    }

    function submit(form) {
        var valid = form.hasClass(FORM_INVALID_STRING);
        if (valid) {
            event.preventDefault();
            return;
        }
        form.submit();
    }
});