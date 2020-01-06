package com.epam.prykhodko.service;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.repository.Basket;
import com.epam.prykhodko.repository.Products;

public class BuyAllFromBasketService implements Command {

  private Basket basket;
  private Products products;

  public BuyAllFromBasketService(Basket basket, Products products) {
    this.basket = basket;
    this.products = products;
  }

  @Override
  public void execute() {
    System.out.println("Sum of your order: " + basket.buyAll(products));
  }
}
