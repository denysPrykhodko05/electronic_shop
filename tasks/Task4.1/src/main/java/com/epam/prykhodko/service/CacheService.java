package com.epam.prykhodko.service;

import com.epam.prykhodko.task1.entity.Product;
import java.util.Map.Entry;
import java.util.Set;

public interface CacheService {

  Set<Entry<Product, Integer>> get();

  void put(Product product, int amount);
}