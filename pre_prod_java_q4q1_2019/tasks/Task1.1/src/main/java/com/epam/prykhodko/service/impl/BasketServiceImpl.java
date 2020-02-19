package com.epam.prykhodko.service.impl;

import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.repository.BasketRepository;
import com.epam.prykhodko.repository.CacheRepository;
import com.epam.prykhodko.service.BasketService;
import java.util.Map;

public class BasketServiceImpl implements BasketService {

  private BasketRepository basketRepository;
  private CacheRepository cacheRepository;

  public BasketServiceImpl(BasketRepository basketRepository, CacheRepository cacheRepository) {
    this.basketRepository = basketRepository;
    this.cacheRepository = cacheRepository;
  }

  @Override
  public void add(Product product, int amount) {
    basketRepository.add(product, amount);
    cacheRepository.put(product, amount);
  }

  @Override
  public Map<Product, Integer> get() {
    return basketRepository.get();
  }

  @Override
  public void clear() {
    basketRepository.clear();
  }

}
