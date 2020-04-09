package com.nure.prykhodko.mananger;

import com.nure.prykhodko.constants.DBConstants;
import com.nure.prykhodko.constants.LoggerMessagesConstants;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class ConnectionManager {

    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class);
    private DataSource dataSource;

    public ConnectionManager() {
        Context context = null;

        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup(DBConstants.DB_URL);
        } catch (NamingException e) {
            LOGGER.error(LoggerMessagesConstants.ERR_CANNOT_CREATE_DATASOURCE);
        }
    }

    public Connection getConnection() {
        Connection con = null;

        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error(LoggerMessagesConstants.ERR_CANNOT_OBTAIN_CONNECTION);
        }
        return con;
    }

}
