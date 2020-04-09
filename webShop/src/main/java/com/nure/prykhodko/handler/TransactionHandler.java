package com.nure.prykhodko.handler;

import com.nure.prykhodko.functioninterface.DAOInterface;
import com.nure.prykhodko.mananger.ConnectionManager;
import com.nure.prykhodko.mananger.TransactionManager;
import com.nure.prykhodko.constants.DBConstants;
import com.nure.prykhodko.constants.LoggerMessagesConstants;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Objects;
import org.apache.log4j.Logger;

public class TransactionHandler {

    private static final Logger LOGGER = Logger.getLogger(TransactionHandler.class);

    public TransactionHandler(ConnectionManager connectionManager) {
        ConnectionHolder.setConnection(connectionManager.getConnection());
    }

    public <T> T invokeTransaction(DAOInterface<T> method) {
        TransactionManager transactionManager = new TransactionManager();
        Connection connection = ConnectionHolder.getConnection();
        String savepointInvokeTransaction = DBConstants.SAVEPOINT_INVOKE_TRANSACTION;
        connection = checkConnection(connection);

        try {
            Savepoint savepoint = connection.setSavepoint(savepointInvokeTransaction);
            connection.setAutoCommit(false);
            T t = method.exec();
            connection.commit();
            return t;

        } catch (SQLException e) {
            transactionManager.rollback(connection);
            LOGGER.error(LoggerMessagesConstants.ERR_CAN_NOT_TO_PERFORM_TRANSACTION);
        } finally {
            transactionManager.closeConnection(connection);
        }

        return null;
    }

    public <T> T invokeWithoutTransaction(DAOInterface<T> method) {
        TransactionManager transactionManager = new TransactionManager();
        Connection connection = ConnectionHolder.getConnection();
        checkConnection(connection);
        try {
            return method.exec();
        } catch (SQLException e) {
            LOGGER.error(LoggerMessagesConstants.ERR_CANNOT_PERFORM_OPERATION);
        } finally {
            transactionManager.closeConnection(connection);
        }

        return null;
    }

    private Connection checkConnection(Connection connection) {
        if (Objects.isNull(connection)) {
            ConnectionHolder.setConnection(new ConnectionManager().getConnection());
            return ConnectionHolder.getConnection();
        }
        return connection;
    }

}
