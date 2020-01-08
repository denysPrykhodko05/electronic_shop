package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.service.OrderService;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.util.ReadWrapper;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    Entry<Date, Map<Product, Integer>> order = null;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    Date date = null;

    while (date == null) {
      System.out.println("Enter date(dd/MM/yyyy HH:mm: ");
      try {
        date = formatter.parse( ReadWrapper.readLine());
      } catch (ParseException | IOException e) {
        date=null;
        System.out.println("Incorrect input. Try again!!!");
      }
    }
    System.out.println(orderService.getNearestOrder(date));
  }
}
