package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.service.BasketService;

public class GetAllFromBasketCommand implements Command {

  private BasketService basketService;

  public GetAllFromBasketCommand(BasketService basketService) {
    this.basketService=basketService;
  }

  @Override
  public void execute() {
    basketService.getAll().forEach(System.out::println);
  }
}
