package com.epam.prykhodko.service;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.repository.Basket;

public class AddToBasketService implements Command {

  private Basket basket;

  public AddToBasketService(Basket basket) {
    this.basket = basket;
  }

  @Override
  public void execute() {
    basket.add();
  }
}
