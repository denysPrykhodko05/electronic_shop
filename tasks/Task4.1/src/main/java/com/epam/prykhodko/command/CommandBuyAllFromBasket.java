package com.epam.prykhodko.command;

import com.epam.prykhodko.repository.Basket;
import com.epam.prykhodko.repository.Products;
import commandInterface.Command;

public class CommandBuyAllFromBasket implements Command {

  private Basket basket;
  private Products products;

  public CommandBuyAllFromBasket(Basket basket, Products products) {
    this.basket = basket;
    this.products = products;
  }

  @Override
  public void execute() {
    System.out.println("Sum of your order: " + basket.buyAll(products));
  }
}
