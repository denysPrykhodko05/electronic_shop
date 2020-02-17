package com.epam.prykhodko.creator.reflect;

import com.epam.prykhodko.entity.input.InputType;
import com.epam.prykhodko.entity.products.Notebook;
import java.util.Locale;

public class NotebookCreator extends ProductCreator {

  public NotebookCreator(InputType inputType, Locale locale) {
    super(inputType, locale);
  }

  @Override
  public Notebook create() {
    final Notebook notebook = new Notebook(super.create());
    super.setParameters(notebook);
    return notebook;
  }

}
