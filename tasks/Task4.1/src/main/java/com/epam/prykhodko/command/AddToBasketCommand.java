package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.service.impl.BasketService;
import com.epam.prykhodko.service.impl.CacheService;
import com.epam.prykhodko.service.impl.ProductService;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.util.ConsoleHelper;
import java.io.IOException;
import java.util.InputMismatchException;

public class AddToBasketCommand implements Command {

  private final com.epam.prykhodko.service.BasketService basketService;
  private final com.epam.prykhodko.service.ProductService productService;
  private final com.epam.prykhodko.service.CacheService cacheService;

  public AddToBasketCommand(BasketService basketService, ProductService productService,
      CacheService cacheService) {
    this.basketService = basketService;
    this.productService = productService;
    this.cacheService = cacheService;
  }

  @Override
  public void execute() {
    int amount = 0;
    int productId = 0;
    Product product=null;
    while (amount <= 0 && productId <= 0) {
      try {
        System.out.println("Enter product id: ");
        productId = ConsoleHelper.readInt();
        product = productService.getById(productId);
        if (product == null) {
          productId=0;
          System.out.println("Incorrect input. Try again!!!");
          continue;
        }
        System.out.println("Enter amount: ");
        amount = ConsoleHelper.readInt();
        if (amount <= 0) {
          System.out.println("Incorrect input. Try again!!!");
        }
      } catch (InputMismatchException | NumberFormatException | IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }
    }
    basketService.add(product, amount);
    cacheService.put(product, amount);
  }
}
