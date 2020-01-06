package com.epam.prykhodko.controller;

import com.epam.prykhodko.command.AddToBasketCommand;
import com.epam.prykhodko.command.CheckOrdersForGivenPeriodCommand;
import com.epam.prykhodko.command.ExitCommand;
import com.epam.prykhodko.command.FindOrderForNearestDateCommand;
import com.epam.prykhodko.command.GetAllFromBasketCommand;
import com.epam.prykhodko.command.GetAllProductsCommand;
import com.epam.prykhodko.command.GetLastFiveProductsCommand;
import com.epam.prykhodko.command.InavalidNumberCommand;
import com.epam.prykhodko.command.MakeOrderCommand;
import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.repository.BasketRepository;
import com.epam.prykhodko.repository.OrderRepository;
import com.epam.prykhodko.repository.ProductRepository;
import com.epam.prykhodko.service.BasketService;
import com.epam.prykhodko.service.OrderService;
import com.epam.prykhodko.service.ProductService;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShopView {

  private static Command invalidCommandNumber;
  private static BasketRepository basketRepository;
  private static ProductRepository productRepository;
  private static OrderRepository orderRepository;
  private static Map<Integer, Command> commandMap;
  private static BasketService basketService;
  private static ProductService productService;
  private static OrderService orderService;

  static {
    basketRepository = new BasketRepository();
    productRepository = new ProductRepository();
    commandMap = new HashMap<>();
    orderRepository = new OrderRepository();

    basketService=new BasketService(basketRepository);
    productService = new ProductService(productRepository);
    orderService = new OrderService(orderRepository);

    commandInit();
  }

  public static void main(String[] args) {

    final Scanner input = new Scanner(System.in);

    int command = -1;
    while (command != 0) {
      System.out.println("Enter:\n0 - EXIT\n"
          + "1 - Show all products\n"
          + "2 - Show basket\n"
          + "3 - Buy all products\n"
          + "4 - Show least 5 products in bucket\n"
          + "5 - Make order\n"
          + "6 - Get order for given period\n"
          + "7 - Find order for nearest date");
      command = input.nextInt();
      commandMap.getOrDefault(command, invalidCommandNumber).execute();
    }
  }

  private static void commandInit() {
    Command exit = new ExitCommand();
    Command getAll = new GetAllProductsCommand(productRepository);
    Command addToBasket = new AddToBasketCommand(basketService,productService);
    Command getAllFromBasket = new GetAllFromBasketCommand(basketService);
    Command getLastFiveOrders = new GetLastFiveProductsCommand(basketService);
    Command makeOrder = new MakeOrderCommand(orderService,basketService);
    Command getOrder = new CheckOrdersForGivenPeriodCommand(orderService);
    Command findOrderForNearestDate = new FindOrderForNearestDateCommand(orderService);
    invalidCommandNumber = new InavalidNumberCommand();

    commandMap.put(0, exit);
    commandMap.put(1, getAll);
    commandMap.put(2, addToBasket);
    commandMap.put(3, getAllFromBasket);
    commandMap.put(4, getLastFiveOrders);
    commandMap.put(5, makeOrder);
    commandMap.put(6, getOrder);
    commandMap.put(7, findOrderForNearestDate);
  }
}
