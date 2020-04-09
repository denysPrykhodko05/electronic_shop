package com.nure.prykhodko.mananger;

import com.nure.prykhodko.handler.ConnectionHolder;
import com.nure.prykhodko.constants.LoggerMessagesConstants;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import org.apache.log4j.Logger;

public class TransactionManager {

    private static final Logger LOGGER = Logger.getLogger(TransactionManager.class);

    public void closeConnection(Connection connection) {
        if (Objects.nonNull(connection)) {

            try {
                connection.close();
                ConnectionHolder.removeConnection();
            } catch (SQLException e) {
                LOGGER.error(LoggerMessagesConstants.ERR_CANNOT_CLOSE_CONNECTION);
            }

        }
    }

    public void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error(LoggerMessagesConstants.ERR_CANNOT_CLOSE_ROLLBACK);
        }
    }

}
