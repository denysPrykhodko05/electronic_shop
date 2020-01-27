package com.epam.prykhodko.creator.reflect;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.epam.prykhodko.entity.invoke.input.impl.RandomInput;
import java.util.Locale;
import org.junit.jupiter.api.Test;

class NotebookCreatorTest {

  @Test
  void createShouldReturnNotebook() {
    NotebookCreator notebookCreator = new NotebookCreator(new RandomInput(), new Locale("ru"));
    assertNotNull(notebookCreator.create());
  }
}