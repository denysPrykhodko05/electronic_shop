package com.epam.prykhodko.constants;

public abstract class Constants {

    //Form strings
    public static final String USER_PERSONAL_DATA_REGEX = "^\\w+";
    public static final String LOGIN_REGEX = "^\\w{3,16}$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z]+)*$";
    public static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*])[\\w!@#$%^&*]{8,}$";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String CO_PASSWORD = "confirmPassword";
    public static final String LOGIN = "login";
    public static final String POLICY = "privacy-policy";
    public static final String MAILS = "mails";
    public static final String REG_CAPTCHA = "regCaptcha";
    public static final String EMPTY_STRING = "";

    //Captcha strings
    public static final String CAPTCHA = "captcha";
    public static final String CAPTCHA_KEY = "captchaKey";
    public static final String CAPTCHA_VALUE = "captchaValue";

    //User strings
    public static final String USER_KEY = "userKey";
    public static final String USER_DATA = "userData";

    //Erorrs strings
    public static final String CONTAINS_LOGIN = "contains-login";
    public static final String CONTAINS_EMAIL = "contains-email";
    public static final String USER_LOGIN_EXISTS = "User with this login is exists";
    public static final String USER_EMAIL_EXISTS = "User with this email is exists";
    public static final String TIMES_UP = "Times up";
    public static final String INCORRECT_INPUT = "Incorrect input of ";
    public static final String YOU_DONT_CHOOSE = "You don't choose ";
    public static final String INCORRECT_CAPTCHA = "Incorrect captcha";

    //Other strings
    public static final String TIMER = "timer";
    public static final String SESSION = "session";
    public static final String COOKIE = "cookie";
    public static final String HIDDEN_FIELD = "hiddenField";
    public static final String USERS = "users";
    public static final String KEEPERS = "keepers";
    public static final String ERRORS = "errors";
    public static final String REGISTRATION_JSP_LINK = "jsp/registration.jsp";
}
