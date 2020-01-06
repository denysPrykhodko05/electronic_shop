package com.epam.prykhodko.service;

import static java.lang.Math.abs;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.repository.Basket;
import com.epam.prykhodko.repository.Order;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class FindOrderForNearestDateService implements Command {

  private Order order;

  public FindOrderForNearestDateService(Order order) {
    this.order = order;
  }

  @Override
  public void execute() {
    Basket basket = null;
    final Scanner input = new Scanner(System.in);
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = null;

    System.out.println("Enter date(dd/MM/yyyy HH:mm:ss: ");
    try {
      date = formatter.parse(input.nextLine());
    } catch (ParseException e) {
      e.printStackTrace();
    }

    basket = findMin(order.getAll(), date);
    new GetAllFromBasketService(basket).execute();
  }

  private Basket findMin(Map<Date, Basket> basketMap, Date date) {
    Basket basket = null;
    long min = Integer.MAX_VALUE;
    for (Entry<Date, Basket> entry : basketMap.entrySet()) {
      long time = abs(entry.getKey().getTime() - date.getTime());
      if (time < min) {
        min = time;
        basket = entry.getValue();
      }
    }
    return basket;
  }
}
