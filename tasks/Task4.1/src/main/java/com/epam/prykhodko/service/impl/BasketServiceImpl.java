package com.epam.prykhodko.service.impl;

import com.epam.prykhodko.repository.BasketRepository;
import com.epam.prykhodko.repository.CacheRepository;
import com.epam.prykhodko.service.BasketService;
import com.epam.prykhodko.task1.entity.ProductRepository;
import java.util.Map;

public class BasketServiceImpl implements BasketService {

  private BasketRepository basketRepository;
  private CacheRepository cacheRepository;

  public BasketServiceImpl(BasketRepository basketRepository, CacheRepository cacheRepository) {
    this.basketRepository = basketRepository;
    this.cacheRepository = cacheRepository;
  }

  @Override
  public void add(ProductRepository product, int amount) {
    basketRepository.add(product, amount);
    cacheRepository.put(product, amount);
  }

  @Override
  public Map<ProductRepository, Integer> get() {
    return basketRepository.get();
  }

  @Override
  public void clear() {
    basketRepository.clear();
  }

}
