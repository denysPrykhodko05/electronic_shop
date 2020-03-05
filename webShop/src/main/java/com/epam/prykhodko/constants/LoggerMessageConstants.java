package com.epam.prykhodko.constants;

public abstract class LoggerMessageConstants {

    //Connection constants
    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";
    public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection from the pool";
    public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";
    public static final String ERR_CANNOT_CLOSE_RESULT_SET = "Cannot close a result set";
    public static final String ERR_CANNOT_CLOSE_ROLLBACK = "Cannot rollback";

    //DAO exceptions
    public static final String ERR_CANNOT_ADD_USER = "Cannot add user";
    public static final String ERR_CANNOT_GET_ALL_USERS = "Cannot get all users";
    public static final String ERR_CANNOT_GET_USER_BY_ID = "Cannot get user by id";
    public static final String ERR_CANNOT_DELETE_USER_BY_LOGIN = "Cannot delete user by login";

    //Filter messages
    public static final String INFO_LOG_OUT_FILTER_START =  "Log out filter start";
    public static final String INFO_LOG_OUT_FILTER_DESTROY=  "Log out filter destroy";
}
