package com.epam.prykhodko.creator;

import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.task1.entity.Smartphone;
import java.util.Locale;

public class SmartphoneCreator extends TelephoneCreator {

  public SmartphoneCreator(InputType inputType, Locale locale) {
    super(inputType, locale);
  }

  @Override
  public Product create() {
    final Smartphone smartphone = new Smartphone((Smartphone) super.create());
    setParameters(smartphone);
    return smartphone;
  }
}
