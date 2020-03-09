package com.epam.prykhodko.handler;

import static com.epam.prykhodko.constants.DBConstants.SAVEPOINT_INVOKE_TRANSACTION;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_PERFORM_OPERATION;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CAN_NOT_TO_PERFORM_TRANSACTION;

import com.epam.prykhodko.functioninterface.DAOInterface;
import com.epam.prykhodko.mananger.ConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import org.apache.log4j.Logger;

public class TransactionHandler {

    private static final Logger LOGGER = Logger.getLogger(TransactionHandler.class);

    public TransactionHandler(ConnectionManager connectionManager) {
        ConnectionHolder.setConnection(connectionManager.getConnection());
    }

    public <T> T invokeTransaction(DAOInterface<T> method) {
        TransactionManager transactionManager = new TransactionManager();
        Connection connection = ConnectionHolder.getConnection();
        String savepointInvokeTransaction = SAVEPOINT_INVOKE_TRANSACTION;
        try {
            Savepoint savepoint = connection.setSavepoint(savepointInvokeTransaction);
            connection.setAutoCommit(false);
            T t = method.exec();
            connection.commit();
            return t;
        } catch (SQLException e) {
            transactionManager.rollback(connection);
            LOGGER.error(ERR_CAN_NOT_TO_PERFORM_TRANSACTION);
        } finally {
            transactionManager.closeConnection(connection);
        }
        return null;
    }

    public <T> T invokeWithoutTransaction(DAOInterface<T> method) {
        TransactionManager transactionManager = new TransactionManager();
        Connection connection = ConnectionHolder.getConnection();
        try {
            return method.exec();
        } catch (SQLException e) {
            LOGGER.error(ERR_CANNOT_PERFORM_OPERATION);
        } finally {
            transactionManager.closeConnection(connection);
        }
        return null;
    }

}
