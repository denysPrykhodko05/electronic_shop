package com.epam.prykhodko.service.userservicedaoimpl;

import com.epam.prykhodko.bean.RegFormBean;
import com.epam.prykhodko.dao.impl.UserDAO;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.service.UserService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserServiceDAOImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceDAOImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void add(RegFormBean formBean) {
        User user = createUser(formBean);
        add(user);
    }

    @Override
    public void add(User user) {
        userDAO.add(user);
    }

    @Override
    public boolean deleteByLogin(String login) {
        User user = getByLogin(login);
        return userDAO.delete(user);
    }

    @Override
    public boolean delete(User user) {
        return userDAO.delete(user);
    }

    @Override
    public User getByLogin(String login) {
        List<User> users = userDAO.getAll();
        Optional<User> user = users.stream().filter(e -> e.getLogin().equals(login)).findFirst();
        return user.orElse(null);
    }

    @Override
    public boolean isContains(User user) {
        List<User> users = userDAO.getAll();
        Optional<User> userTemp = users.stream()
            .filter(e -> (e.getLogin().equals(user.getLogin()) && e.getPassword().equals(user.getPassword())))
            .findFirst();
        return Objects.nonNull(userTemp.orElse(null));
    }

    @Override
    public User createUser(RegFormBean regFormBean) {
        return new User(1, regFormBean.getName(), regFormBean.getSurname(), regFormBean.getEmail(), regFormBean.getLogin(), regFormBean.getPassword(), 1);
    }
}
