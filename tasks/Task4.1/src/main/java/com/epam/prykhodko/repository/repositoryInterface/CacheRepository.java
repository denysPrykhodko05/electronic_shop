package com.epam.prykhodko.repository.repositoryInterface;

import com.epam.prykhodko.task1.entity.Product;
import java.util.Map;

public interface CacheRepository {

  void put(Product product, int amount);
  Map<Product,Integer> get();
}
