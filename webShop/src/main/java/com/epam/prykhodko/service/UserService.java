package com.epam.prykhodko.service;

import com.epam.prykhodko.entity.User;

public interface UserService {

    void add(User user);

    boolean deleteByLogin(String login);

    boolean delete(User user);

    User getByLogin(String login);
}
