package com.epam.prykhodko.controller;

import static com.epam.prykhodko.constant.Constants.THREAD_INTERRUPTED;
import static java.lang.System.lineSeparator;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.command.impl.AddToBasketCommand;
import com.epam.prykhodko.command.impl.AddToProductListByReflectCommand;
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
import com.epam.prykhodko.util.LocaleUtil;
import com.epam.prykhodko.util.files.FileSerializationImpl;
import com.epam.prykhodko.util.readers.ConsoleHelper;
import com.epam.prykhodko.util.readers.InputUtil;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import task9.com.epam.prykhodko.controller.ServersController;

public class ShopViewTask7 {

  private static final Logger LOGGER = Logger.getLogger("controller");
  private static final FileSerializationImpl serializer = new FileSerializationImpl(
      "container.txt");
  private static final Object monitor = new Object();
  private static Command invalidCommandNumber;
  private static ProductRepository productRepositoryImpl;
  private static Map<Integer, Command> commandMap;
  private static BasketServiceImpl basketServiceImpl;
  private static ProductServiceImpl productServiceImpl;
  private static OrderServiceImpl orderServiceImpl;
  private static CacheServiceImpl cacheServiceImpl;

  public static void main(String[] args) {
    DOMConfigurator.configure(
        "resources" + File.separator + "log4j.xml");
    productRepositoryImpl = serializer.read();
    repositoryInit();
    commandInit();
    synchronized (monitor) {
      Thread server = new Thread(new ServersController(monitor, productServiceImpl));
      server.setDaemon(true);
      server.start();
      try {
        monitor.wait();
      } catch (InterruptedException e) {
        LOGGER.error(THREAD_INTERRUPTED);
      }
      int command = -1;
      while (command != 0) {
        LOGGER.info("Enter: " + lineSeparator()
            + "0 - EXIT" + lineSeparator()
            + "1 - Show all products" + lineSeparator()
            + "2 - Add to basket" + lineSeparator()
            + "3 - Show all from basket" + lineSeparator()
            + "4 - Show least 5 products in basket" + lineSeparator()
            + "5 - Make order" + lineSeparator()
            + "6 - Get order for given period" + lineSeparator()
            + "7 - Find order for nearest date" + lineSeparator()
            + "8 - Add product to catalog");
        try {
          command = ConsoleHelper.readInt();
        } catch (IOException | NumberFormatException e) {
          LOGGER.error("Incorrect input. Try again!!!");
          continue;
        }
        commandMap.getOrDefault(command, invalidCommandNumber).execute();
      }
    }
    serializer.write(productRepositoryImpl);
  }

  private static void repositoryInit() {
    BasketRepository basketRepository = new BasketRepositoryImpl();
    CacheRepository cacheRepository = new CacheRepositoryImpl();
    commandMap = new HashMap<>();
    OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();

    basketServiceImpl = new BasketServiceImpl(basketRepository, cacheRepository);
    productServiceImpl = new ProductServiceImpl(productRepositoryImpl);
    orderServiceImpl = new OrderServiceImpl(orderRepository);
    cacheServiceImpl = new CacheServiceImpl(cacheRepository);
  }

  private static void commandInit() {
    int counter = 0;
    InputType inputType = InputUtil.inputType();
    Locale locale = LocaleUtil.getLocale();

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

    Command addToProductList = new AddToProductListByReflectCommand(inputType, locale,
        productServiceImpl);
    commandMap.put(counter++, addToProductList);

    invalidCommandNumber = new InavalidNumberCommand();

  }
}