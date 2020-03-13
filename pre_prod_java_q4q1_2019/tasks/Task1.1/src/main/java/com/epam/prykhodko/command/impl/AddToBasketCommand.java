package com.epam.prykhodko.command.impl;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.service.BasketService;
import com.epam.prykhodko.service.CacheService;
import com.epam.prykhodko.service.ProductService;
import com.epam.prykhodko.util.readers.ConsoleHelper;
import java.io.IOException;
import java.util.InputMismatchException;

public class AddToBasketCommand implements Command {

  private final BasketService basketService;
  private final ProductService productService;
  private final CacheService cacheService;

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
    Product product = null;
    while (amount <= 0 && productId <= 0) {
      try {
        System.out.println("Enter product id: ");
        productId = ConsoleHelper.readInt();
        product = productService.getById(productId);
        if (product == null) {
          productId = 0;
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