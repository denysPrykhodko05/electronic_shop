package com.epam.prykhodko.service;

import com.epam.prykhodko.task1.entity.Product;
import java.util.Map;

public interface BasketService {

  void add(Product product, int amount);

  Map<Product, Integer> get();

  void clear();
}
