package com.epam.prykhodko.repository;

import com.epam.prykhodko.repository.repositoryInterface.CacheRepository;
import com.epam.prykhodko.task1.entity.Product;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CacheRepositoryImpl implements CacheRepository {

  private final int MAX_CAPACITY = 5;
  private Map<Product, Integer> cache;

  public CacheRepositoryImpl() {
    cache = new LinkedHashMap(MAX_CAPACITY, 0.75f, true) {
      @Override
      protected boolean removeEldestEntry(Entry eldest) {
        return size() > MAX_CAPACITY;
      }
    };
  }

  public void put(Product product, int amount){
    cache.put(product,amount);
  }

  @Override
  public Map<Product, Integer> get() {
    return cache;
  }
}
