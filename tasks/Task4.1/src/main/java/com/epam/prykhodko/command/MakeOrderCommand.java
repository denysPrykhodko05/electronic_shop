package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.service.BasketService;
import com.epam.prykhodko.service.OrderService;
import com.epam.prykhodko.task1.entity.Product;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class MakeOrderCommand implements Command {

  private OrderService orderService;
  private BasketService basketService;

  public MakeOrderCommand(OrderService orderService, BasketService basketService) {
    this.orderService = orderService;
    this.basketService = basketService;
  }

  @Override
  public void execute() {
    Map<Product, Integer> basket = new HashMap<>();
    final Scanner productInput = new Scanner(System.in);
    final Scanner dateInput = new Scanner(System.in);
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    Date date = null;

    System.out.println("Enter date(dd/MM/yyyy HH:mm: ");
    try {
      date = formatter.parse(dateInput.nextLine());
    } catch (ParseException e) {
      e.printStackTrace();
    }
    for (Entry<Product, Integer> entry : basketService.getAll()) {
      basket.put(entry.getKey(), entry.getValue());
    }
    orderService.add(date, basket);
    basketService.getBasket().clear();
  }
}
