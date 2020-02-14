package com.epam.prykhodko.controller;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.command.impl.AddToBasketCommand;
import com.epam.prykhodko.command.impl.AddToProductListCommand;
import com.epam.prykhodko.command.impl.CheckOrdersForGivenPeriodCommand;
import com.epam.prykhodko.command.impl.ExitCommand;
import com.epam.prykhodko.command.impl.FindOrderForNearestDateCommand;
import com.epam.prykhodko.command.impl.GetAllFromBasketCommand;
import com.epam.prykhodko.command.impl.GetAllProductsCommand;
import com.epam.prykhodko.command.impl.GetLastFiveProductsCommand;
import com.epam.prykhodko.command.impl.InavalidNumberCommand;
import com.epam.prykhodko.command.impl.MakeOrderCommand;
import com.epam.prykhodko.entity.input.InputType;
import com.epam.prykhodko.repository.BasketRepository;
import com.epam.prykhodko.repository.CacheRepository;
import com.epam.prykhodko.repository.ProductRepository;
import com.epam.prykhodko.repository.impl.BasketRepositoryImpl;
import com.epam.prykhodko.repository.impl.CacheRepositoryImpl;
import com.epam.prykhodko.repository.impl.OrderRepositoryImpl;
import com.epam.prykhodko.service.impl.BasketServiceImpl;
import com.epam.prykhodko.service.impl.CacheServiceImpl;
import com.epam.prykhodko.service.impl.OrderServiceImpl;
import com.epam.prykhodko.service.impl.ProductServiceImpl;
import com.epam.prykhodko.util.files.FileSerializationImpl;
import com.epam.prykhodko.util.readers.ConsoleHelper;
import com.epam.prykhodko.util.readers.InputUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShopView {

  private static final FileSerializationImpl serializer = new FileSerializationImpl(
      "container.txt");

  private static Command invalidCommandNumber;
  private static ProductRepository productRepositoryImpl;
  private static Map<Integer, Command> commandMap;
  private static BasketServiceImpl basketServiceImpl;
  private static ProductServiceImpl productServiceImpl;
  private static OrderServiceImpl orderServiceImpl;
  private static CacheServiceImpl cacheServiceImpl;

  public static void main(String[] args) {
    productRepositoryImpl = serializer.read();
    entityInit();
    int command = -1;
    while (command != 0) {
      System.out.println("Enter:\n0 - EXIT\n"
          + "1 - Show all products\n"
          + "2 - Add to basket\n"
          + "3 - Show all from basket\n"
          + "4 - Show least 5 products in basket\n"
          + "5 - Make order\n"
          + "6 - Get order for given period\n"
          + "7 - Find order for nearest date\n"
          + "8 - Add product to catalog");
      try {
        command = ConsoleHelper.readInt();
      } catch (IOException | NumberFormatException e) {
        System.out.println("Incorrect input. Try again!!!");
        continue;
      }
      commandMap.getOrDefault(command, invalidCommandNumber).execute();
    }
    serializer.write(productRepositoryImpl);
  }

  private static void entityInit() {

    BasketRepository basketRepository = new BasketRepositoryImpl();
    CacheRepository cacheRepository = new CacheRepositoryImpl();
    commandMap = new HashMap<>();
    OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();

    basketServiceImpl = new BasketServiceImpl(basketRepository, cacheRepository);
    productServiceImpl = new ProductServiceImpl(productRepositoryImpl);
    orderServiceImpl = new OrderServiceImpl(orderRepository);
    cacheServiceImpl = new CacheServiceImpl(cacheRepository);

    commandInit();
  }

  private static void commandInit() {
    int counter = 0;
    InputType inputType = InputUtil.inputType();

    Command exit = new ExitCommand();
    commandMap.put(counter++, exit);

    Command getAll = new GetAllProductsCommand(productRepositoryImpl);
    commandMap.put(counter++, getAll);

    Command addToBasket = new AddToBasketCommand(basketServiceImpl, productServiceImpl,
        cacheServiceImpl);
    commandMap.put(counter++, addToBasket);

    Command getAllFromBasket = new GetAllFromBasketCommand(basketServiceImpl);
    commandMap.put(counter++, getAllFromBasket);

    Command getLastFiveOrders = new GetLastFiveProductsCommand(cacheServiceImpl);
    commandMap.put(counter++, getLastFiveOrders);

    Command makeOrder = new MakeOrderCommand(orderServiceImpl, basketServiceImpl);
    commandMap.put(counter++, makeOrder);

    Command getOrder = new CheckOrdersForGivenPeriodCommand(orderServiceImpl);
    commandMap.put(counter++, getOrder);

    Command findOrderForNearestDate = new FindOrderForNearestDateCommand(orderServiceImpl);
    commandMap.put(counter++, findOrderForNearestDate);

    Command addToProductList = new AddToProductListCommand(inputType, productServiceImpl);
    commandMap.put(counter++, addToProductList);

    invalidCommandNumber = new InavalidNumberCommand();

  }
}
