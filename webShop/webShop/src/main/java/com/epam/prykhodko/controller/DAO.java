package com.epam.prykhodko.controller;

import com.epam.prykhodko.dao.entity_dao.UserDAO;
import com.epam.prykhodko.dao.entity_dao.entity_dao_impl.UserDAOImpl;
import com.epam.prykhodko.entity.User;

public class DAO {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImpl();
        User user = new User("Ivan","Ivanov","ivan@gmail.com","ivan1","Aadssd@12",1);
        userDAO.add(user);
    }
}
