package com.epam.prykhodko.strategies;

import com.epam.prykhodko.task1.entity.Product;

public interface InputType {
  void setProduct(Product product);
  void input();
}
