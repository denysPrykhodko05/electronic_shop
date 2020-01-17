package com.epam.prykhodko.repository;

import com.epam.prykhodko.task1.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

  void productInit();

  List<Product> get();

  Optional<Product> getById(int id);

  void add(Product product);
}
