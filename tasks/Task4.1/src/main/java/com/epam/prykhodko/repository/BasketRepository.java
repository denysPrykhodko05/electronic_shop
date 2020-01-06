package com.epam.prykhodko.repository;

import com.epam.prykhodko.task1.entity.Product;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BasketRepository implements Cloneable {

  private int count = 0;
  private final int MAX_CAPACITY = 5;
  private Map<Product, Integer> cache;
  private Map<Product, Integer> basket;

  public BasketRepository() {
    basket = new HashMap<>();
    cache = new LinkedHashMap(MAX_CAPACITY, 0.75f, true) {
      @Override
      protected boolean removeEldestEntry(Entry eldest) {
        return size() > MAX_CAPACITY;
      }
    };
  }

  public void add(Product product, int amount) {
    basket.put(product, amount);
    cache.put(product, amount);
  }

  public Map<Product, Integer> get() {
    return basket;
  }


  public Map<Product, Integer> getCache() {
    return cache;
  }
}
