package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.service.impl.BasketService;
import com.epam.prykhodko.service.impl.OrderService;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.util.ConsoleHelper;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MakeOrderCommand implements Command {

  private final OrderService orderService;
  private final BasketService basketService;

  public MakeOrderCommand(OrderService orderService, BasketService basketService) {
    this.orderService = orderService;
    this.basketService = basketService;
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

    Map<Product, Integer> basket = new HashMap<>(basketService.get());
    orderService.add(date, basket);
    basketService.clear();
  }
}
