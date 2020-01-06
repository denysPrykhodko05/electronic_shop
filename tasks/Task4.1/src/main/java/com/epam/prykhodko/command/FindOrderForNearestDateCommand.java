package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.service.OrderService;
import com.epam.prykhodko.task1.entity.Product;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class FindOrderForNearestDateCommand implements Command {

  private OrderService orderService;

  public FindOrderForNearestDateCommand(OrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public void execute() {
    Entry<Date,Map<Product,Integer>> order = null;
    final Scanner input = new Scanner(System.in);
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    Date date = null;

    System.out.println("Enter date(dd/MM/yyyy HH:mm: ");
    try {
      date = formatter.parse(input.nextLine());
    } catch (ParseException e) {
      e.printStackTrace();
    }

    System.out.println(orderService.getNearestOrder(date));
  }
}
