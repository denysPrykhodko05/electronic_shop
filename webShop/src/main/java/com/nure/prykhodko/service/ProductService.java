package com.nure.prykhodko.service;

import com.nure.prykhodko.entity.products.Product;
import java.util.List;

public interface ProductService {

    Product add(Product entity);

    boolean delete(Product entity);

    List<Product> getAll();

    List<String> getAllManufactures();

    List<String> getAllCategories();

    List<Product> getFilteredProducts(String query);

    Product getById(int id);
}
