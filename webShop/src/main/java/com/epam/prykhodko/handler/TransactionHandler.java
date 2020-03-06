package com.epam.prykhodko.handler;

import com.epam.prykhodko.functioninterface.DAOInterface;
import com.epam.prykhodko.mananger.ConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class TransactionHandler {

    private static final Logger LOGGER = Logger.getLogger(TransactionHandler.class);

    public TransactionHandler(ConnectionManager connectionManager) {
        ConnectionHolder.setConnection(connectionManager.getConnection());
    }

    public <T> T invokeTransaction(DAOInterface<T> method) {
        Connection connection = ConnectionHolder.getConnection();
        try {
            connection.setAutoCommit(false);
            return method.exec();
        } catch (SQLException e) {
            //TODO
        } finally {
            //TODO
        }
        return null;
    }

    public <T> T invokeWithoutTransaction(DAOInterface<T> method) {
        try {
            return method.exec();
        } catch (SQLException e) {
            //TODO
        } finally {
            //TODO
        }
        return null;
    }

}
