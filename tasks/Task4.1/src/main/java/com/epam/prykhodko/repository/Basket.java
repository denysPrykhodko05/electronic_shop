package com.epam.prykhodko.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Basket implements Cloneable{

  private int count = 0;
  private Map<Integer, Integer> history;
  private Map<Integer, Integer> basket;

  public Basket() {
    basket = new HashMap<>();
    history = new LinkedHashMap<>();
  }

  public void add(int productId, int amount) {
    basket.put(productId, amount);
    history.put(count++, productId);
  }

  public Map<Integer, Integer> get() {
    return basket;
  }

  public Set<Entry<Integer, Integer>> getAll() {
    return basket.entrySet();
  }

  public BigDecimal buyAll(Products products) {
    final BigDecimal[] price = {new BigDecimal(0)};
    getAll().forEach((e) -> {
      products.getAll().stream().filter((p) -> p.getId() == e.getKey())
          .forEach(
              p -> price[0] = price[0].add(p.getPrice().multiply(new BigDecimal(e.getValue()))));
    });
    basket.clear();
    return price[0];
  }

  public Map<Integer, Integer> getHistory() {
    return history;
  }
}
