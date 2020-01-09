package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.service.impl.BasketService;

public class GetAllFromBasketCommand implements Command {

  private final BasketService basketService;

  public GetAllFromBasketCommand(BasketService basketService) {
    this.basketService = basketService;
  }

  @Override
  public void execute() {
    basketService.get().forEach((e,k)-> System.out.println(e));
  }
}
