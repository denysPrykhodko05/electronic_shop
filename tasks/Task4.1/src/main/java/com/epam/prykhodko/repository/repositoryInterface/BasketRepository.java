package com.epam.prykhodko.repository.repositoryInterface;

import com.epam.prykhodko.task1.entity.Product;
import java.util.Map;

public interface BasketRepository extends Cloneable {

  void add(Product product, int amount);

  Map<Product, Integer> get();

  void clear();
}
