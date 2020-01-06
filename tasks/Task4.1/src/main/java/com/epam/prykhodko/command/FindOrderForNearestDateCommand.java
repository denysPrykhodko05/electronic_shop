package com.epam.prykhodko.command;

import static java.lang.Math.abs;

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
    Map<Product,Integer> order = null;
    final Scanner input = new Scanner(System.in);
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = null;

    System.out.println("Enter date(dd/MM/yyyy HH:mm:ss: ");
    try {
      date = formatter.parse(input.nextLine());
    } catch (ParseException e) {
      e.printStackTrace();
    }

    order = findMin(orderService.get(), date);
    System.out.println(order);
  }

  private Map<Product,Integer> findMin(Map<Date, Map<Product,Integer>> basketMap, Date date) {
    Map<Product,Integer> order = null;
    long min = Integer.MAX_VALUE;
    for (Entry<Date, Map<Product,Integer>> entry : basketMap.entrySet()) {
      long time = abs(entry.getKey().getTime() - date.getTime());
      if (time < min) {
        min = time;
        order = entry.getValue();
      }
    }
    return order;
  }
}
