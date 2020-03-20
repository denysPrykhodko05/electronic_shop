package com.epam.prykhodko.service.userservicedaoimpl;

import com.epam.prykhodko.dao.UserDAO;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.handler.TransactionHandler;
import com.epam.prykhodko.service.UserService;
import java.util.List;

public class MysqlUserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final TransactionHandler transactionHandler;

    public MysqlUserServiceImpl(UserDAO userDAO, TransactionHandler transactionHandler) {
        this.userDAO = userDAO;
        this.transactionHandler = transactionHandler;
    }

    @Override
    public User add(User user) {
        return transactionHandler.invokeTransaction(
            () -> userDAO.add(user));
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
    public List<User> getAll() {
        return transactionHandler.invokeWithoutTransaction(userDAO::getAll);
    }
}
