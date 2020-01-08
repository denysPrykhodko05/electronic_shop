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
import com.epam.prykhodko.repository.BasketRepositoryImpl;
import com.epam.prykhodko.repository.CacheRepositoryImpl;
import com.epam.prykhodko.repository.OrderRepositoryImpl;
import com.epam.prykhodko.repository.ProductRepositoryImpl;
import com.epam.prykhodko.repository.repositoryInterface.CacheRepository;
import com.epam.prykhodko.service.BasketService;
import com.epam.prykhodko.service.CacheService;
import com.epam.prykhodko.service.OrderService;
import com.epam.prykhodko.service.ProductService;
import com.epam.prykhodko.util.ReadWrapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShopView {

  private static Command invalidCommandNumber;
  private static ProductRepositoryImpl productRepositoryImpl;
  private static Map<Integer, Command> commandMap;
  private static BasketService basketService;
  private static ProductService productService;
  private static OrderService orderService;
  private static CacheService cacheService;
  static {
    BasketRepositoryImpl basketRepository = new BasketRepositoryImpl();
    productRepositoryImpl = new ProductRepositoryImpl();
    CacheRepository cacheRepository = new CacheRepositoryImpl();
    commandMap = new HashMap<>();
    OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();

    basketService = new BasketService(basketRepository);
    productService = new ProductService(productRepositoryImpl);
    orderService = new OrderService(orderRepository);
    cacheService = new CacheService(cacheRepository);

    commandInit();
  }

  public static void main(String[] args) {
    int command = -1;
    while (command != 0) {
      System.out.println("Enter:\n0 - EXIT\n"
          + "1 - Show all products\n"
          + "2 - Add to basket\n"
          + "3 - Show all from basket\n"
          + "4 - Show least 5 products in basket\n"
          + "5 - Make order\n"
          + "6 - Get order for given period\n"
          + "7 - Find order for nearest date");
      try {
        command =  Integer.parseInt(ReadWrapper.readLine());
      } catch (IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }

      commandMap.getOrDefault(command, invalidCommandNumber).execute();
    }
  }

  private static void commandInit() {
    int counter = 0;
    Command exit = new ExitCommand();
    Command getAll = new GetAllProductsCommand(productRepositoryImpl);
    Command addToBasket = new AddToBasketCommand(basketService, productService,cacheService);
    Command getAllFromBasket = new GetAllFromBasketCommand(basketService);
    Command getLastFiveOrders = new GetLastFiveProductsCommand(cacheService);
    Command makeOrder = new MakeOrderCommand(orderService, basketService);
    Command getOrder = new CheckOrdersForGivenPeriodCommand(orderService);
    Command findOrderForNearestDate = new FindOrderForNearestDateCommand(orderService);
    invalidCommandNumber = new InavalidNumberCommand();

    commandMap.put(counter++, exit);
    commandMap.put(counter++, getAll);
    commandMap.put(counter++, addToBasket);
    commandMap.put(counter++, getAllFromBasket);
    commandMap.put(counter++, getLastFiveOrders);
    commandMap.put(counter++, makeOrder);
    commandMap.put(counter++, getOrder);
    commandMap.put(counter++, findOrderForNearestDate);
  }
}
