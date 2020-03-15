package com.epam.prykhodko.dao;

import com.epam.prykhodko.entity.products.Product;
import java.util.List;

public interface DAOProduct extends DAO<Product> {

    List<String> getDefineParameter(String parameter);

    List<Product> getFilteredEntity(String query);

}
