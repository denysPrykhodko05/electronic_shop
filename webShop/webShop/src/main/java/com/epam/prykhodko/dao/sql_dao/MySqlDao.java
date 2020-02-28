package com.epam.prykhodko.dao.sql_dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import javax.naming.Context;
public class MySqlDao {
    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method you must configure the Date Source and the Connections Pool in your
     * WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return A DB connection.
     */

    private static final Logger LOG = Logger.getLogger(MySqlDao.class);

    public Connection getConnection(){
        BasicDataSource bs = new BasicDataSource();
        bs.set
        Connection con=null;
        Context initContext = null;
        try {
            initContext = new InitialContext();
            //Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)initContext.lookup("jdbc/electronic_store");
            con = ds.getConnection("root","root");
        } catch (NamingException e) {
            //TODO
        } catch (SQLException e) {
            //TODO
        }
        return con;
    }

    public void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                //TODO
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
            }
        }
    }
}
