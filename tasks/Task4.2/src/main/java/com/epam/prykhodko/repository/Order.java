package com.epam.prykhodko.repository;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Order {

  private Map<Date, Basket> orders;

  public Order() {
    orders = new TreeMap<>();
  }

  public void addOrder(Date time, Basket basket) {
    this.orders.put(time, basket);
  }

  public Map<Date,Basket> getAll(){
    return orders;
  }

}
