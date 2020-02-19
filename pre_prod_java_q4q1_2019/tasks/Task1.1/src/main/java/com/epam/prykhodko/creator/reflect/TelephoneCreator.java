package com.epam.prykhodko.creator.reflect;

import com.epam.prykhodko.entity.input.InputType;
import com.epam.prykhodko.entity.products.Telephone;
import java.util.Locale;

public class TelephoneCreator extends ProductCreator {

  public TelephoneCreator(InputType inputType, Locale locale) {
    super(inputType, locale);
  }

  @Override
  public Telephone create() {
    final Telephone telephone = new Telephone(super.create());
    setParameters(telephone);
    return telephone;
  }
}
