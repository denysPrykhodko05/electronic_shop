package com.epam.prykhodko.service;

import com.epam.prykhodko.task1.entity.ProductRepository;
import java.util.Map;

public interface BasketService {

  void add(ProductRepository product, int amount);

  Map<ProductRepository, Integer> get();

  void clear();
}
