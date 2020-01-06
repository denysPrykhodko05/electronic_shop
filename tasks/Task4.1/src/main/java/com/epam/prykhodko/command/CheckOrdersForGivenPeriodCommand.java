package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.service.OrderService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CheckOrdersForGivenPeriodCommand implements Command {

  private OrderService orderService;

  public CheckOrdersForGivenPeriodCommand(OrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public void execute() {
    final Scanner input = new Scanner(System.in);
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    Date date = null;
    Date date1 = null;

    try {
      System.out.println("Enter first date(dd/MM/yyyy HH:mm: ");
      date = formatter.parse(input.nextLine());
      System.out.println("Enter last date(dd/MM/yyyy HH:mm: ");
      date1 = formatter.parse(input.nextLine());
    } catch (ParseException e) {
      e.printStackTrace();
    }
    System.out.println(orderService.getOrdersInGivenPeriod(date,date1));
  }
}
