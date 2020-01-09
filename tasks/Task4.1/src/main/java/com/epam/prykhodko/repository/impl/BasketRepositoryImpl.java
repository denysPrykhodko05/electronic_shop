package com.epam.prykhodko.repository.impl;

import com.epam.prykhodko.repository.BasketRepository;
import com.epam.prykhodko.task1.entity.Product;
import java.util.HashMap;
import java.util.Map;

public class BasketRepositoryImpl implements BasketRepository {

  private Map<Product, Integer> basket;

  public BasketRepositoryImpl() {
    basket = new HashMap<>();
  }

  public void add(Product product, int amount) {
    basket.put(product, amount);
  }

  public Map<Product, Integer> get() {
    return basket;
  }

  @Override
  public void clear() {
    basket.clear();
  }
}
