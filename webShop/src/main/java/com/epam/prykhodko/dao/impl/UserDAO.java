package com.epam.prykhodko.dao.impl;

import com.epam.prykhodko.dao.DAO;
import com.epam.prykhodko.dao.MySqlDAO;
import com.epam.prykhodko.entity.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class UserDAO extends MySqlDAO implements DAO<User> {

    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void add(User user) {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM user");
            while (rs.next()){
                System.out.println(rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(stmt);
            close(rs);
            close(connection);
        }
    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) {

    }
}
