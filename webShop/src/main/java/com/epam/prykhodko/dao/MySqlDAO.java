package com.epam.prykhodko.dao;

import static com.epam.prykhodko.constants.DBConstants.DB_URL;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_CLOSE_CONNECTION;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_CLOSE_RESULT_SET;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_CLOSE_ROLLBACK;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_CLOSE_STATEMENT;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_OBTAIN_CONNECTION;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class MySqlDAO {

    private static final Logger LOGGER = Logger.getLogger(MySqlDAO.class);

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

    /**
     * Closes a statement object.
     */
    public void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOGGER.info(ERR_CANNOT_CLOSE_STATEMENT);
            }
        }
    }

    /**
     * Closes a result set object.
     */
    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOGGER.info(ERR_CANNOT_CLOSE_RESULT_SET);
            }
        }
    }

    /**
     * Closes resources.
     */
    public void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }

    /**
     * Rollbacks a connection.
     *
     * @param con Connection to be rollbacked.
     */
    public void rollBack(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOGGER.info(ERR_CANNOT_CLOSE_ROLLBACK);
            }
        }
    }
}
