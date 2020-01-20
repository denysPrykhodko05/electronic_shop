package com.epam.prykhodko.repository;

import com.epam.prykhodko.task1.entity.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends Serializable {

  void productInit();

  List<Product> get();

  Optional<Product> getById(int id);

  void add(Product product);

  void add(ArrayList<Product> product);
}
