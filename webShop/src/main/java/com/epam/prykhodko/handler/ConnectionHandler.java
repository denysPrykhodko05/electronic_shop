package com.epam.prykhodko.handler;

import static com.epam.prykhodko.constants.DBConstants.DB_URL;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_CREATE_INITIAL_CONTEXT;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_OBTAIN_CONNECTION;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class ConnectionHandler {

    private static final Logger LOGGER = Logger.getLogger(ConnectionHandler.class);
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
            LOGGER.error(ERR_CANNOT_OBTAIN_CONNECTION);
        } catch (NamingException e) {
            LOGGER.error(ERR_CANNOT_CREATE_INITIAL_CONTEXT);
        }
    }

    public static Connection getConnection() {
        return threadLocal.get();
    }

    public static void removeConnection() {
        threadLocal.remove();
    }

}
