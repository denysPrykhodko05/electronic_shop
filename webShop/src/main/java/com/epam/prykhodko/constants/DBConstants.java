package com.epam.prykhodko.constants;

public abstract class DBConstants {

    //User credential
    public static final String DB_URL = "java:comp/env/jdbc/electronic_store";

    //USERDAO QUERIES
    public static final String ADD_USER = "INSERT INTO user(name, surname, login, email, password, role_id, avatar_path) VALUES(?,?,?,?,?,?,?)";
    public static final String GET_ALL_USERS = "SELECT id, name, surname, login, email, password, role_id, avatar_path FROM user";
    public static final String GET_USER_BY_ID = "SELECT id, name, surname, login, email, password, role_id, avatar_path FROM user WHERE id =?";
    public static final String DELETE_USER_BY_LOGIN = "DELETE FROM user WHERE login=?";

    //Other db constants
    public static final String SAVEPOINT_INVOKE_TRANSACTION = "savepointInvokeTransaction";
}