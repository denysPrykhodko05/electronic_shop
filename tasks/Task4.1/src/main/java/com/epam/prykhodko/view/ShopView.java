package com.epam.prykhodko.view;

import com.epam.prykhodko.command.CommandAddToBasket;
import com.epam.prykhodko.command.CommandBuyAllFromBasket;
import com.epam.prykhodko.command.CommandExit;
import com.epam.prykhodko.command.CommandGetAllFromBasket;
import com.epam.prykhodko.command.CommandGetAllProducts;
import com.epam.prykhodko.command.CommandInavalidNumber;
import com.epam.prykhodko.repository.Basket;
import com.epam.prykhodko.repository.Products;
import commandInterface.Command;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShopView {

  private static Command invalidCommandNumber;
  private static Basket basket;
  private static Products products;
  private static Map<Integer, Command> commandMap;

  static {
    basket = new Basket();
    products = new Products();
    commandMap = new HashMap<>();

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
          + "5 - Show least 5 products in bucket");
      command = input.nextInt();
      commandMap.getOrDefault(command, invalidCommandNumber).execute();
    }
  }

  private static void commandInit() {
    Command exit = new CommandExit();
    Command getAll = new CommandGetAllProducts(products);
    Command addToBasket = new CommandAddToBasket(basket);
    Command getAllFromBasket = new CommandGetAllFromBasket(basket);
    Command buyAll = new CommandBuyAllFromBasket(basket, products);
    invalidCommandNumber = new CommandInavalidNumber();

    commandMap.put(0, exit);
    commandMap.put(1, getAll);
    commandMap.put(2, addToBasket);
    commandMap.put(3, getAllFromBasket);
    commandMap.put(4, buyAll);
  }

}
