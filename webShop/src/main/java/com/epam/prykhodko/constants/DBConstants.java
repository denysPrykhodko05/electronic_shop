package com.epam.prykhodko.constants;

public abstract class DBConstants {

    //User credential
    public static final String DB_URL = "java:comp/env/jdbc/electronic_store";

    //USERDAO QUERIES
    public static final String ADD_USER = "INSERT INTO user(name, surname, login, email, password, role_id) VALUES(?,?,?,?,?,?)";
    public static final String GET_ALL_USERS = "SELECT * FROM user";
    public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id =?";
    public static final String DELETE_USER_BY_LOGIN = "DELETE FROM user WHERE login=?";
    public static final String GET_USER_BY_LOGIN = "SELECT user.id, user.name, surname, login, email, password, role.role FROM user INNER JOIN role on role.id = user.role_id WHERE login = ?";

    //PRODUCTDAO QUERIES
    public static final String ADD_PRODUCT = "INSERT INTO product(name, price, manufacture, category) VALUES(?,?,?,?)";
    public static final String GET_ALL_PRODUCTS = "SELECT product.id, product.name, product.price, product.manufacture, product.description, product_category.name as category FROM product inner join product_category on product.category = product_category.id";
    public static final String GET_PRODUCT_BY_ID = "SELECT product.id, product.name, product.price, product.manufacture, product.description, product_category.name as category FROM product inner join product_category on product.category = product_category.id HAVING id = ?";
    public static final String DELETE_PRODUCT_BY_NAME = "DELETE FROM product WHERE name = ?";
    public static final String GET_ALL_MANUFACTURES = "SELECT DISTINCT(manufacture) FROM product";
    public static final String GET_ALL_CATEGORIES = "SELECT name FROM product_category";
    public static final String GET_ALL_PRODUCTS_FROM_LOW_PRICE = " ORDER BY product.price";
    public static final String GET_ALL_PRODUCTS_FROM_HIGH_PRICE = " ORDER BY product.price DESC";
    public static final String GET_ALL_PRODUCTS_FROM_A_Z = " ORDER BY product.name";
    public static final String GET_ALL_PRODUCTS_FROM_Z_A = " ORDER BY product.name DESC";

    //ORDERDAO QUERIES
    public static final String INSERT_INTO_ORDER = "INSERT INTO orders (status, description, date, user_email) VALUES (?,?,?,?)";
    public static final String INSERT_INTO_ORDERED_PRDUCT = "INSERT INTO ordered_product (product_id, order_id, price, amount) VALUES (?,?,?,?)";

    //Other db constants
    public static final String SAVEPOINT_INVOKE_TRANSACTION = "savepointInvokeTransaction";

    //DAO operators
    public static final String WHERE = " WHERE ";
    public static final String PRICE_BETWEEN = " (price between ";
    public static final String AND = " and ";
    public static final String STRING_CLOSE_CIRCLE_BRACKET = ")";
    public static final String STRING_OPEN_CIRCLE_BRACKET = " (";
    public static final String MANUFACTURE_PARAMETER = "manufacture='";
    public static final String STRING_SINGLE_QUOTATION_MARK = "'";
    public static final String OR_WITH_MANUFACTURE = " or manufacture='";
    public static final String PRODUCT_CATEGORY_NAME = "(product_category.name = '";
    public static final String OR_WITH_PRODUCT_CATEGORY_NAME = " or product_category.name = '";
    public static final String LIMIT = " LIMIT ";
    public static final String COMMA = ", ";
}