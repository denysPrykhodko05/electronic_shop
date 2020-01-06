package com.epam.prykhodko.controller;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.repository.Basket;
import com.epam.prykhodko.repository.Order;
import com.epam.prykhodko.repository.Products;
import com.epam.prykhodko.service.AddToBasketService;
import com.epam.prykhodko.service.BuyAllFromBasketService;
import com.epam.prykhodko.service.CheckOrdersForGivenPeriodService;
import com.epam.prykhodko.service.ExitService;
import com.epam.prykhodko.service.FindOrderForNearestDateService;
import com.epam.prykhodko.service.GetAllFromBasketService;
import com.epam.prykhodko.service.GetAllProductsService;
import com.epam.prykhodko.service.GetLastFiveProductsService;
import com.epam.prykhodko.service.InavalidNumberService;
import com.epam.prykhodko.service.MakeOrderService;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShopView {

  private static Command invalidCommandNumber;
  private static Basket basket;
  private static Products products;
  private static Order order;
  private static Map<Integer, Command> commandMap;

  static {
    basket = new Basket();
    products = new Products();
    commandMap = new HashMap<>();
    order = new Order();

    commandInit();
  }

  public static void main(String[] args) {

    final Scanner input = new Scanner(System.in);

    int command = -1;
    while (command != 0) {
      System.out.println("Enter:\n0 - EXIT\n"
          + "1 - Show all products\n"
          + "2 - Add product to basket\n"
          + "3 - Show basket\n"
          + "4 - Buy all products\n"
          + "5 - Show least 5 products in bucket\n"
          + "6 - Make order\n"
          + "7 - Get order for given period\n"
          + "8 - Find order for nearest date");
      command = input.nextInt();
      commandMap.getOrDefault(command, invalidCommandNumber).execute();
    }
  }

  private static void commandInit() {
    Command exit = new ExitService();
    Command getAll = new GetAllProductsService(products);
    Command addToBasket = new AddToBasketService(basket);
    Command getAllFromBasket = new GetAllFromBasketService(basket);
    Command buyAll = new BuyAllFromBasketService(basket, products);
    Command getLastFiveOrders = new GetLastFiveProductsService(basket);
    Command makeOrder = new MakeOrderService(order);
    Command getOrder = new CheckOrdersForGivenPeriodService(order);
    Command findOrderForNearestDate = new FindOrderForNearestDateService(order);
    invalidCommandNumber = new InavalidNumberService();

    commandMap.put(0, exit);
    commandMap.put(1, getAll);
    commandMap.put(2, addToBasket);
    commandMap.put(3, getAllFromBasket);
    commandMap.put(4, buyAll);
    commandMap.put(5, getLastFiveOrders);
    commandMap.put(6, makeOrder);
    commandMap.put(7, getOrder);
    commandMap.put(8, findOrderForNearestDate);
  }
}