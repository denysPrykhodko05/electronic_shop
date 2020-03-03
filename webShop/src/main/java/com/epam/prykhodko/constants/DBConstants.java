package com.epam.prykhodko.constants;

public abstract class DBConstants {

    //User credential
    public static final String DB_URL = "jdbc:mysql://localhost:3306/electronic_store";
    public static final String USER_NAME = "root";
    public static final String USER_PASSWORD = "root";

    //USERDAO QUERIES
    public static final String ADD_USER = "INSERT INTO user(name, surname, login, email, password, role_id) VALUES(?,?,?,?,?,?)";
    public static final String GET_ALL_USERS = "SELECT id, name, surname, login, email, password, role_id FROM user";
    public static final String GET_USER_BY_ID = "SELECT id, name, surname, login, email, password, role_id FROM user WHERE id =?";
    public static final String DELETE_USER_BY_LOGIN = "DELETE FROM user WHERE login=?";
}