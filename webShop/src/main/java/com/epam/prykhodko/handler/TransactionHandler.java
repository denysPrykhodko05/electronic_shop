package com.epam.prykhodko.handler;

import com.epam.prykhodko.functioninterface.DAOInterface;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class TransactionHandler {

    private static final Logger LOGGER = Logger.getLogger(TransactionHandler.class);

    public void invokeTransaction(DAOInterface method) {
        ConnectionHandler.setConnection();
        Connection connection = ConnectionHandler.getConnection();
        try {
            method.exec();
            commit(connection);
        } catch (SQLException e) {
            //TODO
            rollback(connection);
        } finally {
            close(connection);
        }

    }

    public <T> T invokeWithoutTransaction(DAOInterface<T> method) {
        ConnectionHandler.setConnection();
        Connection connection = ConnectionHandler.getConnection();
        try {
            return method.exec();
        } catch (SQLException e) {
            //TODO
        } finally {
            close(connection);
        }
        return null;
    }

    private void commit(Connection connection) {
        try {
            connection.commit();
        } catch (SQLException e) {
            //TODO
        }
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            //TODO
        }
    }

    private void close(Connection connection) {
        try {
            connection.close();
            ConnectionHandler.removeConnection();
        } catch (SQLException e) {
            //TODO
        }
    }
}
