package com.epam.prykhodko.dao;

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
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/electronic_store");
            con = ds.getConnection();

        } catch (NamingException ex) {
            //TODO
            LOGGER.info("naming exception");
        } catch (SQLException e) {
            //TODO
            LOGGER.info("sql exception");
        }catch (Exception e){
            LOGGER.info("exe ");
        }
        return con;
    }

    public void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                //TODO
                LOGGER.info("sql exception");
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
                //TODO
                LOGGER.info("sql exception");
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
                //TODO
                LOGGER.info("sql exception");
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
                //TODO
                LOGGER.info("sql exception");
            }
        }
    }
}
