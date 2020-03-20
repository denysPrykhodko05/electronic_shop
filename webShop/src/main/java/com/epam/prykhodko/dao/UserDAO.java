package com.epam.prykhodko.dao;

import com.epam.prykhodko.entity.User;
import java.util.List;

public interface UserDAO {

    User get(int id);

    List<User> getAll();

    User add(User user);

    boolean delete(User user);

    User getByLogin(String name);

}
