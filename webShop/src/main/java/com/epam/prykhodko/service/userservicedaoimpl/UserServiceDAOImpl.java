package com.epam.prykhodko.service.userservicedaoimpl;

import com.epam.prykhodko.bean.RegFormBean;
import com.epam.prykhodko.dao.DAO;
import com.epam.prykhodko.entity.User;
import com.epam.prykhodko.handler.TransactionHandler;
import com.epam.prykhodko.mananger.ConnectionManager;
import com.epam.prykhodko.service.DAOService;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class UserServiceDAOImpl implements DAOService<User, RegFormBean> {

    private final DAO<User> userDAO;
    private final TransactionHandler transactionHandler = new TransactionHandler(new ConnectionManager());

    public UserServiceDAOImpl(DAO<User> userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User addByForm(RegFormBean formBean) {
        User user = createEntity(formBean);
        return add(user);
    }

    @Override
    public User add(User user) {
        return transactionHandler.invokeTransaction(
            () -> userDAO.add(user));
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
        return transactionHandler.invokeTransaction(() -> userDAO.delete(user));
    }

    @Override
    public User getByName(String login) {
        return transactionHandler.invokeWithoutTransaction(() -> userDAO.getByName(login));
    }

    @Override
    public boolean isContains(User user) {
        return Objects.nonNull(getByName(user.getLogin()));
    }

    @Override
    public List<User> getAll() {
        return transactionHandler.invokeWithoutTransaction(userDAO::getAll);
    }

    @Override
    public User createEntity(RegFormBean form) {
        String password = Base64.getEncoder().encodeToString(form.getPassword().getBytes());
        return new User(form.getName(), form.getSurname(), form.getEmail(), form.getLogin(), password);
    }

}
