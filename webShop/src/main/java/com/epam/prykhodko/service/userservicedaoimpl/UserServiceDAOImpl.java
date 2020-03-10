package com.epam.prykhodko.service.userservicedaoimpl;

import com.epam.prykhodko.bean.RegFormBean;
import com.epam.prykhodko.dao.DAO;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.handler.TransactionHandler;
import com.epam.prykhodko.mananger.ConnectionManager;
import com.epam.prykhodko.service.DAOService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserServiceDAOImpl implements DAOService<User> {

    private final DAO<User> userDAO;
    private final TransactionHandler transactionHandler = new TransactionHandler(new ConnectionManager());

    public UserServiceDAOImpl(DAO<User> userDAO) {
        this.userDAO = userDAO;
    }

    public User add(RegFormBean formBean) {
        User user = createUser(formBean);
        return add(user);
    }

    @Override
    public User add(User entity) {
        return transactionHandler.invokeTransaction(() -> userDAO.add(entity));
    }

    @Override
    public boolean deleteByName(String login) {
        return transactionHandler.invokeWithoutTransaction(() -> {
            User user = getByName(login);
            return userDAO.delete(user);
        });
    }

    @Override
    public boolean delete(User user) {
        return userDAO.delete(user);
    }

    @Override
    public User getByName(String login) {
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
    public List<User> getAll() {
        return userDAO.getAll();
    }

    public User createUser(RegFormBean regFormBean) {
        return new User(1, regFormBean.getName(), regFormBean.getSurname(), regFormBean.getEmail(), regFormBean.getLogin(), regFormBean.getPassword(), 1,
            regFormBean.getAvatarPath());
    }
}
