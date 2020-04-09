package com.nure.prykhodko.dao;

import com.nure.prykhodko.entity.products.Product;
import java.util.List;

public interface ProductDAO {

    Product get(int id);

    List<Product> getAll();

    Product add(Product e);

    boolean delete(Product e);

    List<String> getDefineParameter(String parameter);

    List<Product> getFilteredEntity(String query);

}
