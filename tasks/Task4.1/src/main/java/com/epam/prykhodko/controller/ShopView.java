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
import com.epam.prykhodko.repository.CacheRepository;
import com.epam.prykhodko.repository.impl.BasketRepositoryImpl;
import com.epam.prykhodko.repository.impl.CacheRepositoryImpl;
import com.epam.prykhodko.repository.impl.OrderRepositoryImpl;
import com.epam.prykhodko.repository.impl.ProductRepository;
import com.epam.prykhodko.service.impl.BasketServiceImpl;
import com.epam.prykhodko.service.impl.CacheServiceImpl;
import com.epam.prykhodko.service.impl.OrderServiceImpl;
import com.epam.prykhodko.service.impl.ProductServiceImpl;
import com.epam.prykhodko.util.ConsoleHelper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShopView {

  private static Command invalidCommandNumber;
  private static ProductRepository productRepository;
  private static Map<Integer, Command> commandMap;
  private static BasketServiceImpl basketServiceImpl;
  private static ProductServiceImpl productServiceImpl;
  private static OrderServiceImpl orderServiceImpl;
  private static CacheServiceImpl cacheServiceImpl;

  static {
    BasketRepository basketRepository = new BasketRepositoryImpl();
    productRepository = new ProductRepository();
    CacheRepository cacheRepository = new CacheRepositoryImpl();
    commandMap = new HashMap<>();
    OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();

    basketServiceImpl = new BasketServiceImpl(basketRepository, cacheRepository);
    productServiceImpl = new ProductServiceImpl(productRepository);
    orderServiceImpl = new OrderServiceImpl(orderRepository);
    cacheServiceImpl = new CacheServiceImpl(cacheRepository);

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
        command = ConsoleHelper.readInt();
      } catch (IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }

      commandMap.getOrDefault(command, invalidCommandNumber).execute();
    }
  }

  private static void commandInit() {
    int counter = 0;
    Command exit = new ExitCommand();
    Command getAll = new GetAllProductsCommand(productRepository);
    Command addToBasket = new AddToBasketCommand(basketServiceImpl, productServiceImpl,
        cacheServiceImpl);
    Command getAllFromBasket = new GetAllFromBasketCommand(basketServiceImpl);
    Command getLastFiveOrders = new GetLastFiveProductsCommand(cacheServiceImpl);
    Command makeOrder = new MakeOrderCommand(orderServiceImpl, basketServiceImpl);
    Command getOrder = new CheckOrdersForGivenPeriodCommand(orderServiceImpl);
    Command findOrderForNearestDate = new FindOrderForNearestDateCommand(orderServiceImpl);
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
