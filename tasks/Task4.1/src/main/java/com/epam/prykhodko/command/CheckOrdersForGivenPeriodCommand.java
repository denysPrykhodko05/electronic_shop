package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.service.OrderService;
import com.epam.prykhodko.util.ConsoleHelper;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class CheckOrdersForGivenPeriodCommand implements Command {

  private final OrderService orderService;

  public CheckOrdersForGivenPeriodCommand(OrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public void execute() {
    Date date = null;
    Date date1 = null;

    while (date == null || date1 == null) {
      try {
        System.out.println("Enter first date(dd/MM/yyyy HH:mm: ");
        date = ConsoleHelper.readDate();
        System.out.println("Enter last date(dd/MM/yyyy HH:mm: ");
        date1 = ConsoleHelper.readDate();
      } catch (ParseException | IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }
    }
    System.out.println(orderService.getOrdersInGivenPeriod(date, date1));
  }
}
