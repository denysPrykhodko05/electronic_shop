package com.epam.prykhodko.service;

import java.util.List;

public interface DAOService<E, F> {

    E addByForm(F form);

    E add(E entity);

    boolean deleteByName(String name);

    boolean delete(E entity);

    E getByName(String name);

    boolean isContains(E entity);

    List<E> getAll();

    E createEntity(F form);
}
