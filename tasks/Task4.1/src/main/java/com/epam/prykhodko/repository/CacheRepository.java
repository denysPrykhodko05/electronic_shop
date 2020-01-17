package com.epam.prykhodko.repository;

import com.epam.prykhodko.task1.entity.ProductRepository;
import java.util.Map;

public interface CacheRepository {

  void put(ProductRepository product, int amount);
  Map<ProductRepository,Integer> get();
}
