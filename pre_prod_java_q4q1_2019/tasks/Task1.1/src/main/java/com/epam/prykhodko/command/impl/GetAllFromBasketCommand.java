package com.epam.prykhodko.command.impl;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.service.BasketService;

public class GetAllFromBasketCommand implements Command {

  private final BasketService basketService;

  public GetAllFromBasketCommand(BasketService basketService) {
    this.basketService = basketService;
  }

  @Override
  public void execute() {
    basketService.get().forEach((e, k) -> System.out.println(e));
  }
}
