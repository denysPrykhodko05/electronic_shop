package com.epam.prykhodko.repository;

import com.epam.prykhodko.repository.repositoryInterface.BasketRepository;
import com.epam.prykhodko.repository.repositoryInterface.CacheRepository;
import com.epam.prykhodko.task1.entity.Product;
import java.util.HashMap;
import java.util.Map;

public class BasketRepositoryImpl implements BasketRepository {

  private Map<Product, Integer> basket;
  private CacheRepository cache;

  public BasketRepositoryImpl() {
    basket = new HashMap<>();
    cache = new CacheRepositoryImpl();
  }

  public void add(Product product, int amount) {
    basket.put(product, amount);
    cache.put(product, amount);
  }

  public Map<Product, Integer> get() {
    return basket;
  }

  @Override
  public void clear() {
    basket.clear();
  }
}
