package com.epam.prykhodko.command;

import com.epam.prykhodko.repository.Products;
import commandInterface.Command;

public class CommandGetAllProducts implements Command {

  private Products products;

  public CommandGetAllProducts(Products products) {
    this.products = products;
  }

  @Override
  public void execute() {
    products.getAll().forEach(System.out::println);
  }
}
