package com.epam.prykhodko.service;

import com.epam.prykhodko.task1.entity.ProductRepository;
import java.util.List;

public interface ProductService {


  List<ProductRepository> getAll();

  ProductRepository getById(int id);

  void add(ProductRepository product);
}
