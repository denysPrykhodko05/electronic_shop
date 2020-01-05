package com.epam.prykhodko.service;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.repository.Basket;
import com.epam.prykhodko.repository.Order;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MakeOrderService implements Command {

  private Order order;
  private Basket basket;

  public MakeOrderService(Order order) {
    this.order = order;
  }

  @Override
  public void execute() {
    final Scanner productInput = new Scanner(System.in);
    final Scanner dateInput = new Scanner(System.in);
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = null;
    int command = -1;
    basket = new Basket();
    while (command != 0) {
      new AddToBasketService(basket).execute();
      System.out.println("Do you want add more? (1 - yes/0 - no)");
      command = productInput.nextInt();
    }

    System.out.println("Enter date(dd/MM/yyyy HH:mm:ss: ");
    try {
      date = formatter.parse(dateInput.nextLine());
      order.addOrder(date, basket);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }
}
