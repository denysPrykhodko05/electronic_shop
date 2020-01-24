package com.epam.prykhodko.creator;

import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.task1.entity.Telephone;
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
