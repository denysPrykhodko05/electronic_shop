package com.epam.prykhodko.entities.impl;

import com.epam.prykhodko.entities.ProductType;
import com.epam.prykhodko.task1.entity.Notebook;
import com.epam.prykhodko.task1.entity.Product;

public class AddNotebookCommand implements ProductType {

  @Override
  public Product get() {
    return new Notebook();
  }
}
