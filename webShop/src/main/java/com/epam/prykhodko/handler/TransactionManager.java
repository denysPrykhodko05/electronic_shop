package com.epam.prykhodko.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public class TransactionManager {

    void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            //TODO
        }
    }

    void rollback(Connection connection){
        try {
            connection.rollback();
        } catch (SQLException e) {
            //TODO
        }
    }

}
