package com.epam.prykhodko.creator;

import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.task1.entity.ProductRepository;

public abstract class Creator {

  protected InputType inputType;

  public Creator(InputType inputType) {
    this.inputType = inputType;
  }

  public abstract ProductRepository create();
}
