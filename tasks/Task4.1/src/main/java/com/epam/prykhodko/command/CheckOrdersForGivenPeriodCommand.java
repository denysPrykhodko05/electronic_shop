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

public class CheckOrdersForGivenPeriodCommand implements Command {

  private OrderService orderService;

  public CheckOrdersForGivenPeriodCommand(OrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public void execute() {
    final Scanner input = new Scanner(System.in);
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = null;
    Date date1 = null;

    try {
      System.out.println("Enter first date(dd/MM/yyyy HH:mm:ss: ");
      date = formatter.parse(input.nextLine());
      System.out.println("Enter last date(dd/MM/yyyy HH:mm:ss: ");
      date1 = formatter.parse(input.nextLine());
    } catch (ParseException e) {
      e.printStackTrace();
    }

    final Date dateTemp = date;
    final Date dateTemp1 = date1;
    for (Entry<Date, Map<Product, Integer>> entry : orderService.get().entrySet()) {
      if (entry.getKey().getTime() - dateTemp.getTime() >= 0
          && entry.getKey().getTime() - dateTemp1.getTime() <= 0) {
        entry.getValue().entrySet().forEach(System.out::println);
      }

    }
  }
}
