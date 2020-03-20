package com.epam.prykhodko.service;

import com.epam.prykhodko.entity.products.Product;
import java.util.List;

public interface ProductService {

    Product add(Product entity);

    boolean delete(Product entity);

    Product getByName(String name);

    List<Product> getAll();


    List<String> getAllManufactures();

    List<String> getAllCategories();

    List<Product> getFilteredProducts(String query);

}
