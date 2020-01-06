package com.epam.prykhodko.repository;

import com.epam.prykhodko.task1.entity.Product;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class OrderRepository {

  private TreeMap<Date, Map<Product, Integer>> orders;

  public OrderRepository() {
    orders = new TreeMap<>();
  }

  public void add(Date time, Map<Product, Integer> products) {
    this.orders.put(time, products);
  }

  public TreeMap<Date, Map<Product, Integer>> get() {
    return orders;
  }

}
