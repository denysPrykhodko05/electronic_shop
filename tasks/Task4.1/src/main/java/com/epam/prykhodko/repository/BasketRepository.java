package com.epam.prykhodko.repository;

import com.epam.prykhodko.task1.entity.ProductRepository;
import java.io.Serializable;
import java.util.Map;

public interface BasketRepository extends Cloneable, Serializable {

  void add(ProductRepository product, int amount);

  Map<ProductRepository, Integer> get();

  void clear();
}
