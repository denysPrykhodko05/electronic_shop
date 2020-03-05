package com.epam.prykhodko.dao;

import static com.epam.prykhodko.constants.DBConstants.DB_URL;
import static com.epam.prykhodko.constants.DBConstants.SAVEPOINT_NAME;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_CLOSE_CONNECTION;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_CLOSE_RESULT_SET;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_CLOSE_ROLLBACK;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_CREATE_SAVEPOINT;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_OBTAIN_CONNECTION;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Objects;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class TransactionMananger {

    private static final Logger LOGGER = Logger.getLogger(TransactionMananger.class);
    private Savepoint savepoint;


    public Connection getConnection() {
        Connection con = null;
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup(DB_URL);
            con = dataSource.getConnection();
        } catch (SQLException | NamingException e) {
            LOGGER.error(ERR_CANNOT_OBTAIN_CONNECTION);
        }
        return con;
    }

    public void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOGGER.info(ERR_CANNOT_CLOSE_CONNECTION);
            }
        }
    }

    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOGGER.info(ERR_CANNOT_CLOSE_RESULT_SET);
            }
        }
    }

    public void savepoint(Connection connection) {
        if (connection != null) {
            try {
                savepoint = connection.setSavepoint(SAVEPOINT_NAME);
            } catch (SQLException e) {
                LOGGER.error(ERR_CANNOT_CREATE_SAVEPOINT);
            }
        }
    }

    public void commit(Connection connection) throws SQLException {
        if (Objects.nonNull(connection)) {
            connection.commit();
        }
    }

    public void rollBack(Connection con) {
        if (con != null) {
            try {
                con.rollback(savepoint);
            } catch (SQLException ex) {
                LOGGER.info(ERR_CANNOT_CLOSE_ROLLBACK);
            }
        }
    }
}
