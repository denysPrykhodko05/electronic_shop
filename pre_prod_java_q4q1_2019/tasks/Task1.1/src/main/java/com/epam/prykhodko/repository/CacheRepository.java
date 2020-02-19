package com.epam.prykhodko.repository;

import com.epam.prykhodko.entity.products.Product;
import java.util.Map;

public interface CacheRepository {

  void put(Product product, int amount);

  Map<Product, Integer> get();
}
