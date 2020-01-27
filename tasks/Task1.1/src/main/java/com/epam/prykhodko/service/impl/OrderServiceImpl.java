package com.epam.prykhodko.service.impl;

import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.repository.OrderRepository;
import com.epam.prykhodko.service.OrderService;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class OrderServiceImpl implements OrderService {

  private OrderRepository orderRepository;

  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public void add(Date time, Map<Product, Integer> basket) {
    orderRepository.add(time, basket);
  }

  @Override
  public TreeMap<Date, Map<Product, Integer>> get() {
    return orderRepository.get();
  }

  @Override
  public Map<Date, Map<Product, Integer>> getOrdersInGivenPeriod(Date date, Date date1) {
    return orderRepository.getOrdersInGivenPeriod(date, date1);
  }

  @Override
  public Entry<Date, Map<Product, Integer>> getNearestOrder(Date date) {
    if (orderRepository.get().isEmpty()) {
      return null;
    }
    return orderRepository.getNearestOrder(date);
  }
}
