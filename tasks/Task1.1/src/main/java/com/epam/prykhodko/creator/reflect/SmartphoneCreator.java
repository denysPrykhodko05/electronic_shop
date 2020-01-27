package com.epam.prykhodko.creator.reflect;

import com.epam.prykhodko.entity.invoke.input.InputType;
import com.epam.prykhodko.entity.products.Smartphone;
import java.util.Locale;

public class SmartphoneCreator extends TelephoneCreator {

  public SmartphoneCreator(InputType inputType, Locale locale) {
    super(inputType, locale);
  }

  @Override
  public Smartphone create() {
    final Smartphone smartphone = new Smartphone(super.create());
    setParameters(smartphone);
    return smartphone;
  }
}
