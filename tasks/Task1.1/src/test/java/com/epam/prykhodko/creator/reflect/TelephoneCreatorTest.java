package com.epam.prykhodko.creator.reflect;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.epam.prykhodko.entity.invoke.input.impl.RandomInput;
import java.util.Locale;
import org.junit.jupiter.api.Test;

class TelephoneCreatorTest {

  @Test
  void createShouldReturnTelephone() {
    TelephoneCreator creator = new TelephoneCreator(new RandomInput(),new Locale("ru"));
    assertNotNull(creator.create());
  }
}