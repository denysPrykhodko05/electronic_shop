package com.epam.prykhodko.command.impl;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.service.BasketService;
import com.epam.prykhodko.service.OrderService;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.util.ConsoleHelper;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MakeOrderCommand implements Command {

  private final OrderService orderServiceImpl;
  private final BasketService basketServiceImpl;

  public MakeOrderCommand(OrderService orderServiceImpl, BasketService basketServiceImpl) {
    this.orderServiceImpl = orderServiceImpl;
    this.basketServiceImpl = basketServiceImpl;
  }

  @Override
  public void execute() {

    Date date = null;

    while (date == null) {
      System.out.println("Enter date(dd/MM/yyyy HH:mm: ");
      try {
        date = ConsoleHelper.readDate();
      } catch (ParseException | IOException e) {
        System.out.println("Incorrect input");
        date = null;
      }
    }

    Map<Product, Integer> basket = new HashMap<>(basketServiceImpl.get());
    orderServiceImpl.add(date, basket);
    basketServiceImpl.clear();
  }
}
