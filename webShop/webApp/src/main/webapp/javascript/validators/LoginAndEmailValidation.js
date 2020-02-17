$(document).ready(function() {

    loginRealTimeValidation();
    emailRealTimeValidation();

    $(REG_FORM).submit(function() {
        loginValidation($(REG_LOGIN));
        emailValidation($(REG_EMAIL));
        submit($(REG_FORM));
    });

    function loginRealTimeValidation() {
        $(REG_LOGIN).on(REG_INPUT, function() {
            var form = $(REG_FORM);
            var input = $(REG_LOGIN);
            var login = input.val();
            if (login.match(REGEX_LOGIN)) {
                input.removeClass(REG_INVALID).addClass(REG_VALID);
                form.removeClass(REG_FORM_INVALID);
            } else {
                input.removeClass(REG_VALID).addClass(REG_INVALID);
                form.addClass(REG_FORM_INVALID);
            }
        })
    }


    function emailRealTimeValidation() {
        $(REG_EMAIL).on(REG_INPUT, function() {
            var form = $(REG_FORM);
            var input = $(REG_EMAIL);
            var email = input.val();
            if (email.match(EMAIL_REG)) {
                input.removeClass(REG_INVALID).addClass(REG_VALID);
                form.removeClass(REG_FORM_INVALID);
            } else {
                input.removeClass(REG_VALID).addClass(REG_INVALID);
                form.addClass(REG_FORM_INVALID);
            }
        })
    }

    function loginValidation(login) {
        var value = login.val();
        if (!value.match(REGEX_LOGIN)) {
            $(REG_FORM).addClass(REG_FORM_INVALID);
            return;
        }
        $(REG_FORM).removeClass(REG_FORM_INVALID);
    }

    function emailValidation(email) {
        var value = email.val();
        if (!value.match(EMAIL_REG) || $(REG_FORM).hasClass(REG_FORM_INVALID)) {
            $(REG_FORM).addClass(REG_FORM_INVALID);
            return;
        }
        $(REG_FORM).removeClass(REG_FORM_INVALID);
    }

    function submit(form) {
        var valid = form.hasClass(REG_FORM_INVALID);
        var policy = $(REG_PRIVACY_POLICY).prop(REG_CHECKED);
        if (valid) {
            alert(REG_INCORRECT_INPUT)
            event.preventDefault();
            return;
        }
        if (!policy) {
            alert(REG_AGREE_WITH_PRIVACY_POLICY);
            return;
        }
        form.submit();
    }
});