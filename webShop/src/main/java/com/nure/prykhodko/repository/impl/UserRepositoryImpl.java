package com.nure.prykhodko.repository.impl;

import com.nure.prykhodko.entity.User;
import com.nure.prykhodko.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static final List<User> users = new ArrayList<>();

    @Override
    public User add(User user) {
        if (users.add(user)) {
            return user;
        }
        return null;
    }

    @Override
    public List<User> get() {
        return users;
    }

    @Override
    public boolean delete(User user) {
        return users.remove(user);
    }

    @Override
    public List<User> getAll() {
        return users;
    }
}
