package com.epam.prykhodko.service;

import com.epam.prykhodko.task1.entity.Product;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public interface OrderService {

  void add(Date time, Map<Product, Integer> basket);

  TreeMap<Date, Map<Product, Integer>> get();

  Map<Date, Map<Product, Integer>> getOrdersInGivenPeriod(Date date, Date date1);

  Entry<Date, Map<Product, Integer>> getNearestOrder(Date date);
}
