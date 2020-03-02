function dataValidation(password, confirmPassword) {

    var password = password.value;
    var co_password = confirmPassword.value;
    var form = document.getElementById(FORM_NAME);

    if (!REGEX_PASSWORD.exec(password)) {
        var error = document.getElementById(PASSWORD_STRING);
        error.setAttribute(CLASS_STRING, INVALID_STRING);
        alert(INCORRECT_PASSWORD_STRING);
        form.setAttribute(CLASS_STRING, FORM_INVALID_STRING);
        return false;
    }

    var pass = document.getElementById(PASSWORD_STRING);
    pass.setAttribute(CLASS_STRING, VALID_STRING);
    if (password != co_password) {
        var clazz = pass.getAttribute(CLASS_STRING);
        if (clazz != null && clazz.match(INVALID_STRING)) {
            pass.removeAttribute(CLASS_STRING);
            pass.setAttribute(CLASS_STRING, VALID_STRING);
        }
        var error = document.getElementById(CONFIRM_PASSWORD_STRING);
        error.setAttribute(CLASS_STRING, INVALID_STRING)
        alert(INCORRECT_CONFIRM_PASSWORD_STRING);
        form.setAttribute(CLASS_STRING, FORM_INVALID_STRING);
        return false;
    }
    form.removeAttribute(CLASS_STRING);
}