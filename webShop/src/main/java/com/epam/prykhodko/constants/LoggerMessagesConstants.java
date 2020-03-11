package com.epam.prykhodko.constants;

public abstract class LoggerMessagesConstants {

    //Connection constants
    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";
    public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection from the pool";
    public static final String ERR_CANNOT_CLOSE_ROLLBACK = "Cannot rollback";
    public static final String ERR_CANNOT_CREATE_DATASOURCE = "Cannot create data source";
    public static final String ERR_CAN_NOT_TO_PERFORM_TRANSACTION = "can not to perform transaction";
    public static final String ERR_CANNOT_PERFORM_OPERATION = "cannot perform operation";

    //DAO exceptions
    public static final String ERR_CANNOT_ADD_USER = "Cannot add user";
    public static final String ERR_CANNOT_GET_ALL_USERS = "Cannot get all users";
    public static final String ERR_CANNOT_GET_ALL_PRODUCTS = "Cannot get all products";
    public static final String ERR_CANNOT_GET_USER_BY_ID = "Cannot get user by id";
    public static final String ERR_CANNOT_DELETE_USER_BY_LOGIN = "Cannot delete user by login";

    //Filter messages
    public static final String INFO_LOGOUT_FILTER_INIT = "Log out filter init";
    public static final String INFO_LOGOUT_FILTER_DESTROY = "Log out filter destroy";

    //ImageDraw exception
    public static final String ERR_CANNOT_DRAW_AVATAR = "Cannot draw avatar";

    //File exception
    public static final String ERR_CANNOT_LOAD_FILE = "Cannot load file";
}
