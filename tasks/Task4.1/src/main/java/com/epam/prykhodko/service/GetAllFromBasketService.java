package com.epam.prykhodko.service;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.repository.Basket;

public class GetAllFromBasketService implements Command {

  private Basket basket;

  public GetAllFromBasketService(Basket basket) {
    this.basket = basket;
  }

  @Override
  public void execute() {
    basket.getAll().forEach(System.out::println);
  }
}
