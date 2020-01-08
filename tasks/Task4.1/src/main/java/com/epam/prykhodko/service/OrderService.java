package com.epam.prykhodko.service;

import com.epam.prykhodko.repository.repositoryInterface.OrderRepository;
import com.epam.prykhodko.task1.entity.Product;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class OrderService {

  private OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public void add(Date time, Map<Product, Integer> basket) {
    orderRepository.add(time, basket);
  }

  public TreeMap<Date, Map<Product, Integer>> get() {
    return orderRepository.get();
  }

  public Map<Date, Map<Product, Integer>> getOrdersInGivenPeriod(Date date, Date date1) {
    return orderRepository.getOrdersInGivenPeriod(date, date1);
  }

  public  Entry<Date, Map<Product, Integer>> getNearestOrder(Date date) {
    return orderRepository.getNearestOrder(date);
  }
}
