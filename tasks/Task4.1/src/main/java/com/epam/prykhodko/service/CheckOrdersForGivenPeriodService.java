package com.epam.prykhodko.service;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.repository.Order;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CheckOrdersForGivenPeriodService implements Command {

  private Order order;

  public CheckOrdersForGivenPeriodService(Order order) {
    this.order = order;
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
    order.getAll().forEach((e, p) -> {
      if (e.getTime() - dateTemp.getTime() >= 0 && e.getTime() - dateTemp1.getTime() <= 0) {
        p.getAll().forEach(System.out::println);
      }
    });
  }
}
