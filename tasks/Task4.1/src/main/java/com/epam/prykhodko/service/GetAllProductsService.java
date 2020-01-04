package com.epam.prykhodko.service;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.repository.Products;

public class GetAllProductsService implements Command {

  private Products products;

  public GetAllProductsService(Products products) {
    this.products = products;
  }

  @Override
  public void execute() {
    products.getAll().forEach(System.out::println);
  }
}
