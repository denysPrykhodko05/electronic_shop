package com.epam.prykhodko.command;

import com.epam.prykhodko.repository.Basket;
import commandInterface.Command;

public class CommandGetLastFiveProducts implements Command {

  private Basket basket;

  public CommandGetLastFiveProducts(Basket basket) {
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
