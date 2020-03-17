package com.epam.prykhodko.dao;

import com.epam.prykhodko.entity.products.Product;
import java.util.List;

public interface ProductDAO {

    Product get(int id);

    List<Product> getAll();

    Product add(Product e);

    void update(Product e, String[] params);

    boolean delete(Product e);

    Product getByName(String name);

    List<String> getDefineParameter(String parameter);

    List<Product> getFilteredEntity(String query);

}
