package com.epam.prykhodko.handler;

import java.sql.Connection;

public class ConnectionHolder {

    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static void setConnection(Connection connection) {
            threadLocal.set(connection);
    }

    public static Connection getConnection() {
        return threadLocal.get();
    }

    public static void removeConnection() {
        threadLocal.remove();
    }

}
