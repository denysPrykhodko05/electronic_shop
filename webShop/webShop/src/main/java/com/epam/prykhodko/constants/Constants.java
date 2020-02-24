package com.epam.prykhodko.constants;

public class Constants {

    public static final String USER_PERSONAL_DATA_REGEX = "^\\w+";
    public static final String LOGIN_REGEX = "^\\w{3,16}$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z]+)*$";
    public static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*])[\\w!@#$%^&*]{8,}$";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String LOGIN = "login";
    public static final String POLICY = "privacy-policy";
    public static final String MAILS = "mails";
}
