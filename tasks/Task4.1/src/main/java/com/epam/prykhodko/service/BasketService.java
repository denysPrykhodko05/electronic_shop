package com.epam.prykhodko.service;

import com.epam.prykhodko.repository.BasketRepository;
import com.epam.prykhodko.task1.entity.Product;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class BasketService {

  private BasketRepository basket;

  public BasketService(BasketRepository basket) {
    this.basket = basket;
  }

  public void add(Product product, int amount) {
    basket.add(product, amount);
  }

  public Map<Product, Integer> getBasket() {
    return basket.get();
  }

  public Set<Entry<Product, Integer>> getAll() {
    return basket.get().entrySet();
  }

  public Set<Entry<Product, Integer>> getCache() {
    return basket.getCache().entrySet();
  }

}
