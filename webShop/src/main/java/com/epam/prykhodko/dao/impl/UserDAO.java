package com.epam.prykhodko.dao.impl;

import static com.epam.prykhodko.constants.ApplicationConstants.EMAIL;
import static com.epam.prykhodko.constants.ApplicationConstants.ID;
import static com.epam.prykhodko.constants.ApplicationConstants.LOGIN;
import static com.epam.prykhodko.constants.ApplicationConstants.NAME;
import static com.epam.prykhodko.constants.ApplicationConstants.PASSWORD;
import static com.epam.prykhodko.constants.ApplicationConstants.ROLE_ID;
import static com.epam.prykhodko.constants.ApplicationConstants.SURNAME;
import static com.epam.prykhodko.constants.DBConstants.ADD_USER;
import static com.epam.prykhodko.constants.DBConstants.DELETE_USER_BY_LOGIN;
import static com.epam.prykhodko.constants.DBConstants.GET_ALL_USERS;
import static com.epam.prykhodko.constants.DBConstants.GET_USER_BY_ID;
import static com.epam.prykhodko.constants.DBConstants.GET_USER_BY_NAME;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_ADD_USER;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_DELETE_USER_BY_LOGIN;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_GET_ALL_USERS;
import static com.epam.prykhodko.constants.LoggerMessagesConstants.ERR_CANNOT_GET_USER_BY_ID;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.epam.prykhodko.dao.DAO;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.handler.ConnectionHolder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class UserDAO implements DAO<User> {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);

    @Override
    public User get(int id) {
        try (PreparedStatement pstm = ConnectionHolder.getConnection().prepareStatement(GET_USER_BY_ID);
            ResultSet resultSet = pstm.executeQuery()) {
            pstm.setInt(1, id);

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

        try (PreparedStatement preparedStatement = ConnectionHolder.getConnection().prepareStatement(GET_ALL_USERS);
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
    public User add(User user) {
        PreparedStatement pstmt = null;

        try {
            pstmt = ConnectionHolder.getConnection().prepareStatement(ADD_USER);
            fillPreparedStatementByUserData(pstmt, user);

            if (pstmt.executeUpdate() > INTEGER_ZERO) {
                return user;
            }
        } catch (SQLException ex) {
            LOGGER.error(ERR_CANNOT_ADD_USER);
        }
        return null;
    }

    @Override
    public void update(User user, String[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(User user) {
        try (PreparedStatement preparedStatement = ConnectionHolder.getConnection().prepareStatement(DELETE_USER_BY_LOGIN);) {
            preparedStatement.setString(1, user.getLogin());

            if (preparedStatement.executeUpdate() > INTEGER_ZERO) {
                return true;
            }
        } catch (SQLException ex) {
            LOGGER.error(ERR_CANNOT_DELETE_USER_BY_LOGIN);
        }
        return false;
    }

    @Override
    public User getByName(String login) {
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = ConnectionHolder.getConnection().prepareStatement(GET_USER_BY_NAME)) {
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return parseResultSetToUser(resultSet);
            }
        } catch (SQLException ex) {
            LOGGER.error(ERR_CANNOT_DELETE_USER_BY_LOGIN);
        }
        return null;
    }

    @Override
    public List<String> getDefineParameter(String query) {
        throw new UnsupportedOperationException();
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
        int id = resultSet.getInt(ID);
        String name = resultSet.getString(NAME);
        String surName = resultSet.getString(SURNAME);
        String login = resultSet.getString(LOGIN);
        String email = resultSet.getString(EMAIL);
        String password = resultSet.getString(PASSWORD);
        int roleId = resultSet.getInt(ROLE_ID);
        return new User(id, name, surName, email, login, password, roleId);
    }
}
