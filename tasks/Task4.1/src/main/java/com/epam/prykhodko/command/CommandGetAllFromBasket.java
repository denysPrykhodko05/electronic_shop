package com.epam.prykhodko.command;

import com.epam.prykhodko.repository.Basket;
import commandInterface.Command;

public class CommandGetAllFromBasket implements Command {

  private Basket basket;

  public CommandGetAllFromBasket(Basket basket) {
    this.basket = basket;
  }

  @Override
  public void execute() {
    basket.getAll().forEach(System.out::println);
  }
}
