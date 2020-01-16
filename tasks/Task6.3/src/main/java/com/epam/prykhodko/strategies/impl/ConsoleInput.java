package com.epam.prykhodko.strategies.impl;

import static com.epam.prykhodko.constants.Constants.ENTER_ID;

import com.epam.prykhodko.strategies.InputType;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.util.ConsoleScanner;

public class ConsoleInput implements InputType {

  private Product product;

  @Override
  public void input() {
    System.out.println(ENTER_ID);
    product.setId(Integer.parseInt(ConsoleScanner.readLine()));

  }

  @Override
  public void setProduct(Product product) {
    this.product=product;
  }
}
