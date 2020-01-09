package com.epam.prykhodko.repository.impl;

import com.epam.prykhodko.repository.OrderRepository;
import com.epam.prykhodko.task1.entity.Product;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class OrderRepositoryImpl implements OrderRepository {

  private TreeMap<Date, Map<Product, Integer>> orders;

  public OrderRepositoryImpl() {
    orders = new TreeMap<>();
  }

  public void add(Date time, Map<Product, Integer> products) {
    this.orders.put(time, products);
  }

  public TreeMap<Date, Map<Product, Integer>> get() {
    return orders;
  }

  public Map<Date, Map<Product, Integer>> getOrdersInGivenPeriod(Date date, Date date1) {
    return orders.subMap(date, date1);
  }

  @Override
  public  Entry<Date, Map<Product, Integer>> getNearestOrder(Date date) {
    Entry<Date, Map<Product, Integer>> entry = orders.ceilingEntry(date);
    Entry<Date, Map<Product, Integer>> entry1=orders.floorEntry(date);

    if (entry==null && entry1!=null){
      return entry1;
    }

    if (entry1==null && entry!=null){
      return entry;
    }

    if (entry1.getKey().getTime()-entry.getKey().getTime()>0){
      return entry1;
    }
    return entry;
  }
}
