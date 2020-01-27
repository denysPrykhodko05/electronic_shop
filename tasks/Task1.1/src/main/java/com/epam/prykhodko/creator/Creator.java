package com.epam.prykhodko.creator;

import com.epam.prykhodko.entity.invoke.input.InputType;
import com.epam.prykhodko.entity.products.Product;

public abstract class Creator {

  protected final InputType inputType;

  public Creator(InputType inputType) {
    this.inputType = inputType;
  }

  public abstract Product create();
}
