package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.service.BasketService;

public class GetLastFiveProductsCommand implements Command {

  private BasketService basketService;

  public GetLastFiveProductsCommand(BasketService basketService) {
    this.basketService = basketService;
  }

  @Override
  public void execute() {
    basketService.getCache().forEach((e)-> System.out.println(e.getKey()));
  }
}
