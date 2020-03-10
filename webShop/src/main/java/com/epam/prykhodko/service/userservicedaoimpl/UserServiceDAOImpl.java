package com.epam.prykhodko.service.userservicedaoimpl;

import com.epam.prykhodko.bean.RegFormBean;
import com.epam.prykhodko.dao.DAO;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.handler.TransactionHandler;
import com.epam.prykhodko.mananger.ConnectionManager;
import com.epam.prykhodko.service.UserService;
import java.util.Objects;

public class UserServiceDAOImpl implements UserService {

    private final DAO<User> userDAO;
    private final TransactionHandler transactionHandler = new TransactionHandler(new ConnectionManager());

    public UserServiceDAOImpl(DAO<User> userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User add(RegFormBean formBean) {
        User user = createUser(formBean);
        return add(user);
    }

    @Override
    public User add(User user) {
        return transactionHandler.invokeTransaction(
            () -> userDAO.add(user));
    }

    @Override
    public boolean deleteByLogin(String login) {
        return transactionHandler.invokeWithoutTransaction(() -> {
            User user = getByLogin(login);
            return userDAO.delete(user);
        });
    }

    @Override
    public boolean delete(User user) {
        return transactionHandler.invokeTransaction(() -> userDAO.delete(user));
    }

    @Override
    public User getByLogin(String login) {
        return transactionHandler.invokeWithoutTransaction(() -> userDAO.getByLogin(login));
    }

    @Override
    public boolean isContains(User user) {
        return Objects.nonNull(getByLogin(user.getLogin()));
    }

    @Override
    public User createUser(RegFormBean regFormBean) {
        return new User(1, regFormBean.getName(), regFormBean.getSurname(), regFormBean.getEmail(), regFormBean.getLogin(), regFormBean.getPassword(), 1,
            regFormBean.getAvatarPath());
    }
}
