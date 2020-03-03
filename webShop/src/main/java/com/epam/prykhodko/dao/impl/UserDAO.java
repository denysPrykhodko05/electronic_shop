package com.epam.prykhodko.dao.impl;

import static com.epam.prykhodko.constants.DBConstants.ADD_USER;
import static com.epam.prykhodko.constants.DBConstants.GET_ALL_USERS;
import static com.epam.prykhodko.constants.DBConstants.GET_USER_BY_ID;
import static com.epam.prykhodko.constants.ExceptionConstants.ERR_CANNOT_ADD_USER;
import static com.epam.prykhodko.constants.ExceptionConstants.ERR_CANNOT_GET_ALL_USERS;
import static com.epam.prykhodko.constants.ExceptionConstants.ERR_CANNOT_GET_USER_BY_ID;

import com.epam.prykhodko.dao.DAO;
import com.epam.prykhodko.dao.MySqlDAO;
import com.epam.prykhodko.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class UserDAO extends MySqlDAO implements DAO<User> {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);

    @Override
    public User get(int id) {
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(GET_USER_BY_ID);
        ) {
            pstm.setInt(1, id);
            resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                return parseResultSetToUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(ERR_CANNOT_GET_USER_BY_ID);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                users.add(parseResultSetToUser(resultSet));
            }
            return users;
        } catch (SQLException ex) {
            LOGGER.error(ERR_CANNOT_GET_ALL_USERS);
        }
        return users;
    }

    @Override
    public void add(User user) {
        try (Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(ADD_USER)) {
            fillPreparedStatementByUserData(pstmt, user);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.error(ERR_CANNOT_ADD_USER);
        }
    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) {

    }

    private void fillPreparedStatementByUserData(PreparedStatement pstmt, User user) throws SQLException {
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getSurname());
        pstmt.setString(3, user.getLogin());
        pstmt.setString(4, user.getEmail());
        pstmt.setString(5, user.getPassword());
        pstmt.setInt(6, 1);
    }

    private User parseResultSetToUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surName = resultSet.getString("surname");
        String login = resultSet.getString("login");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        int roleId = resultSet.getInt("role_id");
        return new User(id, name, surName, email, login, password, roleId);
    }
}
