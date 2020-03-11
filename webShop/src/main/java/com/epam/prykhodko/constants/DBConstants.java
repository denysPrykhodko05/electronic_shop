package com.epam.prykhodko.constants;

public abstract class DBConstants {

    //User credential
    public static final String DB_URL = "java:comp/env/jdbc/electronic_store";

    //USERDAO QUERIES
    public static final String ADD_USER = "INSERT INTO user(name, surname, login, email, password, role_id) VALUES(?,?,?,?,?,?)";
    public static final String GET_ALL_USERS = "SELECT * FROM user";
    public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id =?";
    public static final String DELETE_USER_BY_LOGIN = "DELETE FROM user WHERE login=?";
    public static final String GET_USER_BY_NAME = "SELECT id, name, surname, login, email, password, role_id FROM user WHERE login = ?";

    //PRODUCTDAO QUERIES
    public static final String ADD_PRODUCT = "INSERT INTO product(name, price, manufacture, category) VALUES(?,?,?,?)";
    public static final String GET_ALL_PRODUCTS = "SELECT product.id, product.name, product.price, product.manufacture, product.description, product_category.name as category FROM product inner join product_category on product.category = product_category.id;";
    public static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?";
    public static final String DELETE_PRODUCT_BY_NAME = "DELETE FROM product WHERE name = ?";
    public static final String GET_ALL_MANUFACTURES = "SELECT DISTINCT(manufacture) FROM product";


    //Other db constants
    public static final String SAVEPOINT_INVOKE_TRANSACTION = "savepointInvokeTransaction";
}