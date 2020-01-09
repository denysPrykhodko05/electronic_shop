package com.epam.prykhodko.service.impl;

import com.epam.prykhodko.repository.BasketRepository;
import com.epam.prykhodko.repository.CacheRepository;
import com.epam.prykhodko.task1.entity.Product;
import java.util.Map;

public class BasketServiceImpl implements com.epam.prykhodko.service.BasketService {

  private BasketRepository basket;
  private CacheRepository cacheRepository;

  public BasketServiceImpl(BasketRepository basket,CacheRepository cacheRepository) {
    this.basket = basket;
    this.cacheRepository = cacheRepository;
  }

  public void add(Product product, int amount) {
    basket.add(product, amount);
    cacheRepository.put(product,amount);
  }

  public Map<Product, Integer> get() {
    return basket.get();
  }

  public void clear(){
    basket.clear();
  }

}
