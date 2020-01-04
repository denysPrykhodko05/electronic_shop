package com.epam.prykhodko.service;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.repository.Basket;

public class GetLastFiveProductsService implements Command {

  private Basket basket;

  public GetLastFiveProductsService(Basket basket) {
    this.basket = basket;
  }

  //what is the information should be in output?
  @Override
  public void execute() {
    basket.getHistory().entrySet().stream().forEach(e -> {
      if (e.getKey() == 5) {
        return;
      }
      System.out.println();
    });
  }
}
