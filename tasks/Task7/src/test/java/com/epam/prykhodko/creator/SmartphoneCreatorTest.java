package com.epam.prykhodko.creator;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.epam.prykhodko.creator.anno.SmartphoneCreator;
import com.epam.prykhodko.entity.input.impl.RandomInput;
import java.util.Locale;
import org.junit.jupiter.api.Test;

class SmartphoneCreatorTest {

  @Test
  void createShouldCreateNewSmartphone() {
    assertNotNull(new SmartphoneCreator(new RandomInput(), new Locale("en")).create());
  }
}