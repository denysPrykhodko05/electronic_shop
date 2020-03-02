package com.epam.prykhodko.service.impl;

import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.repository.UserRepository;
import com.epam.prykhodko.service.UserService;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) {
        userRepository.add(user);
    }

    @Override
    public boolean deleteByLogin(String login) {
        List<User> users = userRepository.get();
        Optional<User> user = users.stream().filter(e -> e.getLogin().equals(login)).findFirst();
        if (user.isPresent()) {
            return users.remove(user.get());
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        return userRepository.delete(user);
    }

    @Override
    public User getByLogin(String login) {
        List<User> users = userRepository.get();
        Optional<User> user = users.stream().filter(e -> e.getLogin().equals(login)).findFirst();
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }
}
