package com.epam.prykhodko.service;

import com.epam.prykhodko.task1.entity.Product;
import java.util.List;

public interface ProductService {

  List<Product> getAll();

  Product getById(int id);
}
