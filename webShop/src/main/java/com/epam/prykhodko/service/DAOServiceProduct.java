package com.epam.prykhodko.service;

import com.epam.prykhodko.entity.products.Product;
import java.util.List;

public interface DAOServiceProduct<E, F> extends DAOService<E, F> {

    List<String> getAllManufactures();

    List<String> getAllCategories();

    List<Product> getFilteredProducts(String query);

}
