package com.epam.prykhodko.creator.reflect;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.epam.prykhodko.entity.input.impl.RandomInput;
import java.util.Locale;
import org.junit.jupiter.api.Test;

class ProductCreatorTest {

  @Test
  void createShouldReturnProduct() {
    ProductCreator productCreator = new ProductCreator(new RandomInput(),new Locale("ru"));
    assertNotNull(productCreator.create());
  }
}