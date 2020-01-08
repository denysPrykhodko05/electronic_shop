package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.service.BasketService;
import com.epam.prykhodko.service.CacheService;
import com.epam.prykhodko.service.ProductService;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.util.ReadWrapper;
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
    while (amount <= 0 && productId <= 0) {
      try {
        System.out.println("Enter product id: ");
        productId = Integer.parseInt(ReadWrapper.readLine());
        if (productService.getById(productId) == null) {
          throw new InputMismatchException();
        }
        System.out.println("Enter amount: ");
        amount = Integer.parseInt(ReadWrapper.readLine());
        if (amount <= 0 || productId <= 0) {
          throw new InputMismatchException();
        }
      } catch (InputMismatchException | NumberFormatException | IOException e) {
        amount = 0;
        productId = 0;
        System.out.println("Incorrect input. Try again!!!");
      }
    }
    Product product = productService.getById(productId);
    basketService.add(product, amount);
    cacheService.put(product, amount);
  }
}
