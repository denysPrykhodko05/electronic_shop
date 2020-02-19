package com.epam.prykhodko.service;

import com.epam.prykhodko.entity.products.Product;
import java.util.List;

public interface ProductService {


  List<Product> getAll();

  Product getById(int id);

  void add(Product product);
}
