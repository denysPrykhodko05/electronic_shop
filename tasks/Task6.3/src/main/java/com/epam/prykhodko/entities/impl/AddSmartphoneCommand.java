package com.epam.prykhodko.entities.impl;

import com.epam.prykhodko.entities.ProductType;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.task1.entity.Smartphone;

public class AddSmartphoneCommand implements ProductType {

  @Override
  public Product get() {
    return new Smartphone();
  }
}
