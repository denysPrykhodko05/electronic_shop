package com.nure.prykhodko.service;

import com.nure.prykhodko.entity.User;
import java.util.List;

public interface UserService {

    User add(User entity);

    boolean delete(User entity);

    User getUser(String login);

    List<User> getAll();
}
