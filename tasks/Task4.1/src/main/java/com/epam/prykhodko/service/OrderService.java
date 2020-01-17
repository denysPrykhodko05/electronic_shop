package com.epam.prykhodko.service;

import com.epam.prykhodko.task1.entity.ProductRepository;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public interface OrderService {

  void add(Date time, Map<ProductRepository, Integer> basket);

  TreeMap<Date, Map<ProductRepository, Integer>> get();

  Map<Date, Map<ProductRepository, Integer>> getOrdersInGivenPeriod(Date date, Date date1);

  Entry<Date, Map<ProductRepository, Integer>> getNearestOrder(Date date);
}
