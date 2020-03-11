package com.epam.prykhodko.service.impl;

import com.epam.prykhodko.bean.RegFormBean;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.repository.UserRepository;
import com.epam.prykhodko.service.DAOService;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements DAOService<User, Object> {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User add(RegFormBean formBean) {
        add(new User(formBean.getName(), formBean.getSurname(), formBean.getEmail(), formBean.getLogin(), formBean.getPassword()));
        return null;
    }

    @Override
    public User addByForm(Object form) {
        return null;
    }

    @Override
    public User createEntity(Object form) {
        return null;
    }

    @Override
    public User add(User user) {
        return userRepository.add(user);
    }

    @Override
    public boolean deleteByName(String login) {
        List<User> users = userRepository.get();
        Optional<User> user = users.stream().filter(e -> e.getLogin().equals(login)).findFirst();
        return user.map(users::remove).orElse(false);
    }

    @Override
    public boolean delete(User user) {
        return userRepository.delete(user);
    }

    @Override
    public User getByName(String login) {
        List<User> users = userRepository.get();
        Optional<User> user = users.stream().filter(e -> e.getLogin().equals(login)).findFirst();
        return user.orElse(null);
    }

    @Override
    public boolean isContains(User newUser) {
        List<User> users = userRepository.get();

        if (users.contains(newUser)) {
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User createUser(RegFormBean regFormBean) {
        return new User(regFormBean.getName(), regFormBean.getSurname(), regFormBean.getEmail(), regFormBean.getLogin(), regFormBean.getPassword());
    }
}