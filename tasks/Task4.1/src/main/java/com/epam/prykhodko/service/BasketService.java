package com.epam.prykhodko.service;

import com.epam.prykhodko.repository.repositoryInterface.BasketRepository;
import com.epam.prykhodko.task1.entity.Product;
import java.util.Map;

public class BasketService {

  private BasketRepository basket;

  public BasketService(BasketRepository basket) {
    this.basket = basket;
  }

  public void add(Product product, int amount) {
    basket.add(product, amount);
  }

  public Map<Product, Integer> get() {
    return basket.get();
  }

  public void clear(){
    basket.clear();
  }

}
