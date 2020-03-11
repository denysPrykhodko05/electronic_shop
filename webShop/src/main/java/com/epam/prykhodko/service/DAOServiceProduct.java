package com.epam.prykhodko.service;

import java.util.List;

public interface DAOServiceProduct<E, F> extends DAOService<E, F> {

    List<String> getAllManufactures();
}
