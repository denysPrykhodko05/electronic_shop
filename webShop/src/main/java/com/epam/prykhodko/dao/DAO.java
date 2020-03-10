package com.epam.prykhodko.dao;

import com.epam.prykhodko.entity.User;
import java.util.List;

public interface DAO<E> {

    E get(int id);

    List<E> getAll();

    User add(E e);

    void update(E e, String[] params);

    boolean delete(E e);

    E getByLogin(String name);
}
