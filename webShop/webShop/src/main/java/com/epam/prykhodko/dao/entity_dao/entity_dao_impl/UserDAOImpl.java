package com.epam.prykhodko.dao.entity_dao.entity_dao_impl;

import com.epam.prykhodko.dao.entity_dao.UserDAO;
import com.epam.prykhodko.dao.sql_dao.MySqlDao;
import com.epam.prykhodko.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl extends MySqlDao implements UserDAO {

    public static final String ADD_USER = "INSERT INTO user(name,surname,login,email,password,role_id) values(?,?,?,?,?,?)";

    @Override
    public boolean add(User user) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            pstmt = connection.prepareStatement(ADD_USER);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getSurname());
            pstmt.setString(3, user.getLogin());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPassword());
            pstmt.setInt(6, user.getRole_id());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            return true;
        } catch ( SQLException ex) {
            //TODO logger
            return false;
        } finally {
            close(pstmt);
            close(rs);
            close(connection);
        }
    }
}
