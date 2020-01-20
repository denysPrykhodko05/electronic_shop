package com.epam.prykhodko.creator;

import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.task1.entity.Product;

public abstract class Creator {

  protected final InputType inputType;

  public Creator(InputType inputType) {
    this.inputType = inputType;
  }

  public abstract Product create();
}
