package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.service.BasketService;
import com.epam.prykhodko.service.ProductService;
import com.epam.prykhodko.task1.entity.Product;
import java.util.Scanner;

public class AddToBasketCommand implements Command {

  private BasketService basketService;
  private ProductService productService;

  public AddToBasketCommand(BasketService basketService, ProductService productService) {
    this.basketService = basketService;
    this.productService = productService;
  }

  @Override
  public void execute() {
    final Scanner input = new Scanner(System.in);
    Product product;
    int productId;
    int amount;
    System.out.println("Enter product id: ");
    productId = input.nextInt();
    System.out.println("Enter amount: ");
    amount = input.nextInt();
    if (amount > 0) {
      product = productService.getById(productId);
      basketService.add(product,amount);
    }
  }
}
