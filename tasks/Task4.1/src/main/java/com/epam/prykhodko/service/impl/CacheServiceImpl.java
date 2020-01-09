package com.epam.prykhodko.service.impl;

import com.epam.prykhodko.repository.CacheRepository;
import com.epam.prykhodko.task1.entity.Product;
import java.util.Map.Entry;
import java.util.Set;

public class CacheServiceImpl implements com.epam.prykhodko.service.CacheService {

  private final CacheRepository cacheRepository;

  public CacheServiceImpl(CacheRepository cacheRepository) {
    this.cacheRepository = cacheRepository;
  }

  public Set<Entry<Product, Integer>> get() {
    return cacheRepository.get().entrySet();
  }

  public void put(Product product, int amount) {
    cacheRepository.put(product, amount);
  }
}
