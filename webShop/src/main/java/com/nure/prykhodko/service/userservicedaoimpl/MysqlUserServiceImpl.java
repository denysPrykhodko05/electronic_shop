package com.nure.prykhodko.service.userservicedaoimpl;

import com.nure.prykhodko.dao.UserDAO;
import com.nure.prykhodko.entity.User;
import com.nure.prykhodko.handler.TransactionHandler;
import com.nure.prykhodko.service.UserService;
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
        return transactionHandler.invokeTransaction(() -> userDAO.add(user));
    }

    @Override
    public boolean delete(User user) {
        return transactionHandler.invokeTransaction(() -> userDAO.delete(user));
    }

    @Override
    public User getUser(String login) {
        return transactionHandler.invokeWithoutTransaction(() -> userDAO.getByLogin(login));
    }

    @Override
    public List<User> getAll() {
        return transactionHandler.invokeWithoutTransaction(userDAO::getAll);
    }
}
