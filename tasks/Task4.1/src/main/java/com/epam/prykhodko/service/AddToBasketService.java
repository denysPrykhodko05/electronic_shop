package com.epam.prykhodko.service;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.repository.Basket;
import java.util.Scanner;

public class AddToBasketService implements Command {

  private Basket basket;

  public AddToBasketService(Basket basket) {
    this.basket = basket;
  }

  @Override
  public void execute() {
    final Scanner input = new Scanner(System.in);
    int productId;
    int amount;
    System.out.println("Enter product id: ");
    productId = input.nextInt();
    System.out.println("Enter amount: ");
    amount = input.nextInt();
    basket.add(productId, amount);
  }
}
