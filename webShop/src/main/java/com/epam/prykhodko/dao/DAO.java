package com.epam.prykhodko.dao;

import java.util.List;

public interface DAO<E> {

    E get(int id);

    List<E> getAll();

    E add(E e);

    void update(E e, String[] params);

    boolean delete(E e);

    E getByName(String name);

    List<String> getDefineParameter(String query);
}
