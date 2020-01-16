package com.epam.prykhodko.strategies.impl;

import com.epam.prykhodko.strategies.InputType;
import com.epam.prykhodko.task1.entity.Product;

public class RandomInput implements InputType {

  private Product product;

  @Override
  public void input() {

  }

  @Override
  public void setProduct(Product product) {
    this.product=product;
  }
}
