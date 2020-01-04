package com.epam.prykhodko.command;

import com.epam.prykhodko.repository.Basket;
import commandInterface.Command;

public class CommandAddToBasket implements Command {

  private Basket basket;

  public CommandAddToBasket(Basket basket) {
    this.basket = basket;
  }

  @Override
  public void execute() {
    basket.add();
  }
}
