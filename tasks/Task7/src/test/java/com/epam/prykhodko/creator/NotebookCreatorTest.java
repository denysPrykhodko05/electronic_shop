package com.epam.prykhodko.creator;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.epam.prykhodko.creator.anno.NotebookCreator;
import com.epam.prykhodko.entity.input.impl.RandomInput;
import java.util.Locale;
import org.junit.jupiter.api.Test;

class NotebookCreatorTest {

  @Test
  void createShouldCreateNewNotebook() {
    assertNotNull(new NotebookCreator(new RandomInput(), new Locale("en")).create());
  }
}