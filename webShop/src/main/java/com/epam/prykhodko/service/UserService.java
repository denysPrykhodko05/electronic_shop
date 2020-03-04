package com.epam.prykhodko.service;

import com.epam.prykhodko.bean.RegFormBean;
import com.epam.prykhodko.entity.User;

public interface UserService {

    void add(RegFormBean formBean);

    void add(User user);

    boolean deleteByLogin(String login);

    boolean delete(User user);

    User getByLogin(String login);

    boolean isContains(User user);

    User createUser(RegFormBean regFormBean);
}
