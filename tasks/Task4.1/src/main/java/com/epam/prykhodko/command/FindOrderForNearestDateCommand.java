package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.service.OrderService;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.util.ConsoleHelper;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

public class FindOrderForNearestDateCommand implements Command {

  private final OrderService orderServiceImpl;

  public FindOrderForNearestDateCommand(OrderService orderServiceImpl) {
    this.orderServiceImpl = orderServiceImpl;
  }

  @Override
  public void execute() {
    Entry<Date, Map<Product, Integer>> order = null;
    Date date = null;

    while (date == null) {
      System.out.println("Enter date(dd/MM/yyyy HH:mm: ");
      try {
        date = ConsoleHelper.readDate();
      } catch (ParseException | IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }
    }
    System.out.println(orderServiceImpl.getNearestOrder(date));
  }
}
