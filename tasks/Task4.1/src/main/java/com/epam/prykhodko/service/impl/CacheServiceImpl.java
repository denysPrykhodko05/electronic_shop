package com.epam.prykhodko.service.impl;

import com.epam.prykhodko.repository.CacheRepository;
import com.epam.prykhodko.service.CacheService;
import com.epam.prykhodko.task1.entity.ProductRepository;
import java.util.Map.Entry;
import java.util.Set;

public class CacheServiceImpl implements CacheService {

  private final CacheRepository cacheRepository;

  public CacheServiceImpl(CacheRepository cacheRepository) {
    this.cacheRepository = cacheRepository;
  }

  @Override
  public Set<Entry<ProductRepository, Integer>> get() {
    return cacheRepository.get().entrySet();
  }

  @Override
  public void put(ProductRepository product, int amount) {
    cacheRepository.put(product, amount);
  }
}
