package com.epam.prykhodko.service;

import com.epam.prykhodko.task1.entity.ProductRepository;
import java.util.Map.Entry;
import java.util.Set;

public interface CacheService {

  Set<Entry<ProductRepository, Integer>> get();

  void put(ProductRepository product, int amount);
}
