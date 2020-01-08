package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.service.OrderService;
import com.epam.prykhodko.util.ReadWrapper;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckOrdersForGivenPeriodCommand implements Command {

  private final OrderService orderService;

  public CheckOrdersForGivenPeriodCommand(OrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public void execute() {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    Date date = null;
    Date date1 = null;

    while (date == null && date1 == null) {
      try {
        System.out.println("Enter first date(dd/MM/yyyy HH:mm: ");
        date = formatter.parse(ReadWrapper.readLine());
        System.out.println("Enter last date(dd/MM/yyyy HH:mm: ");
        date1 = formatter.parse(ReadWrapper.readLine());
      } catch (ParseException | IOException e) {
        date = null;
        date1 = null;
        System.out.println("Incorrect input. Try again!!!");
      }
    }
    System.out.println(orderService.getOrdersInGivenPeriod(date, date1));
  }
}
