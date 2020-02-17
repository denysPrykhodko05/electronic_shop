package com.epam.prykhodko.repository;

import com.epam.prykhodko.entity.products.Product;
import java.io.Serializable;
import java.util.Map;

public interface BasketRepository extends Cloneable, Serializable {

  void add(Product product, int amount);

  Map<Product, Integer> get();

  void clear();
}
