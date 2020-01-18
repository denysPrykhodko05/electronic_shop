package com.epam.prykhodko.command.impl;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.service.OrderService;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.util.ConsoleHelper;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

public class FindOrderForNearestDateCommand implements Command {

  private final OrderService orderService;

  public FindOrderForNearestDateCommand(OrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public void execute() {
    Date date = null;

    while (date == null) {
      System.out.println("Enter date(dd/MM/yyyy HH:mm: ");
      try {
        date = ConsoleHelper.readDate();
      } catch (ParseException | IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }
    }
    Entry<Date, Map<Product, Integer>> entry = orderService.getNearestOrder(date);
    if (entry == null) {
      System.out.println("You haven't orders");
      return;
    }
    System.out.println(entry);
  }
}
