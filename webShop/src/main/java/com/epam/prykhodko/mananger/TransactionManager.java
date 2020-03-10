package com.epam.prykhodko.mananger;

import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_CLOSE_CONNECTION;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_CLOSE_ROLLBACK;

import com.epam.prykhodko.handler.ConnectionHolder;
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
                LOGGER.error(ERR_CANNOT_CLOSE_CONNECTION);
            }

        }
    }

    public void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error(ERR_CANNOT_CLOSE_ROLLBACK);
        }
    }

}
