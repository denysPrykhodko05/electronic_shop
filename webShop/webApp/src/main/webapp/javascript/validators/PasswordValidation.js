function dataValidation(password, confirmPassword) {

    var password = password.value;
    var co_password = confirmPassword.value;

    if (!REGEX_PASSWORD.exec(password)) {
        var error = document.getElementById(REG_PASSWORD);
        error.setAttribute(REG_CLASS, REG_INPUT);
        alert(REG_INCORRECT_PASSWORD);
        return false;
    }

    if (password != co_password) {
        var pass = document.getElementById(REG_PASSWORD);
        var clazz = pass.getAttribute(REG_CLASS);
        if (clazz != null && clazz.match(REG_INVALID)) {
            pass.removeAttribute(REG_CLASS);
            pass.setAttribute(REG_CLASS, REG_VALID);
        }
        var error = document.getElementById(REG_CONFIRM_PASSWORD);
        error.setAttribute(REG_CLASS, REG_INVALID)
        alert(REG_INCORRECT_CONFIRM_PASSWORD);
        return false;
    }
}