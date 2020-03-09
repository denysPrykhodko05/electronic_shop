package com.epam.prykhodko.service;

public interface DAOService<E> {


    E add(E entity);

    boolean deleteByName(String name);

    boolean delete(E entity);

    E getByName(String name);

    boolean isContains(E entity);
}
