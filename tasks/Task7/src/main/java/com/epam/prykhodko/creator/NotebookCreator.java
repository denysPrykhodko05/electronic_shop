package com.epam.prykhodko.creator;

import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.task1.entity.Notebook;
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
