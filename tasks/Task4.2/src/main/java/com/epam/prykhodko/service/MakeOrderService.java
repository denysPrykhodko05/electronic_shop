package com.epam.prykhodko.service;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.repository.Basket;
import com.epam.prykhodko.repository.Order;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class MakeOrderService implements Command {

  private Order order;
  private Basket basket;

  public MakeOrderService(Order order, Basket basket) {
    this.order = order;
    this.basket = basket;
  }

  @Override
  public void execute() {
    final Scanner input = new Scanner(System.in);
    Calendar calendar = new GregorianCalendar();

    System.out.println("Enter year: ");
    calendar.set(Calendar.YEAR, input.nextInt());
    System.out.println("Enter month: ");
    calendar.set(Calendar.MONTH, input.nextInt());
    System.out.println("Enter day: ");
    calendar.set(Calendar.DAY_OF_MONTH, input.nextInt());
    System.out.println("Enter hour: ");
    calendar.set(Calendar.HOUR, input.nextInt());
    System.out.println("Enter minute: ");
    calendar.set(Calendar.MINUTE, input.nextInt());

    order.addOrder(calendar.getTime(), basket);
  }
}
