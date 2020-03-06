package com.epam.prykhodko.dao;

import com.epam.prykhodko.entity.User;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    T get(int id);

    List<T> getAll();

    boolean add(T t);

    void update(T t, String[] params);

    boolean delete(T t);
}
