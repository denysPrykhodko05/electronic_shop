package com.epam.prykhodko.service;

import com.epam.prykhodko.entity.User;
import java.util.List;

public interface UserService {

    User add(User entity);

    boolean delete(User entity);

    User getByLogin(String login);

    List<User> getAll();
}
