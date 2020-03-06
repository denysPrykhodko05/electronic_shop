package com.epam.prykhodko.handler;

import static com.epam.prykhodko.constants.DBConstants.DB_URL;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionHandler {

    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static void setConnection() {
        Connection con;
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(DB_URL);
            con = dataSource.getConnection();
            con.setAutoCommit(false);
            threadLocal.set(con);
        } catch (SQLException e) {
            //TODO
        } catch (NamingException e) {
            //TODO
        }
    }

    public static Connection getConnection() {
        return threadLocal.get();
    }

    public static void removeConnection() {
        threadLocal.remove();
    }

}
