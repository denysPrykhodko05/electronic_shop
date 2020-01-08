package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.service.BasketService;
import com.epam.prykhodko.service.OrderService;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.util.ReadWrapper;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MakeOrderCommand implements Command {

  private final OrderService orderService;
  private final BasketService basketService;

  public MakeOrderCommand(OrderService orderService, BasketService basketService) {
    this.orderService = orderService;
    this.basketService = basketService;
  }

  @Override
  public void execute() {
    Map<Product, Integer> basket = new HashMap<>();

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    Date date = null;

    while (date == null) {
      System.out.println("Enter date(dd/MM/yyyy HH:mm: ");
      try {
        date = formatter.parse(ReadWrapper.readLine());
      } catch (ParseException | IOException e) {
        System.out.println("Incorrect input");
        date = null;
      }
    }
    for (Entry<Product, Integer> entry : basketService.get().entrySet()) {
      basket.put(entry.getKey(), entry.getValue());
    }
    orderService.add(date, basket);
    basketService.clear();
  }
}
