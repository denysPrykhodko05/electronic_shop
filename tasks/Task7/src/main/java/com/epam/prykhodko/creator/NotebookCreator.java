package com.epam.prykhodko.creator;

import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.task1.entity.Notebook;
import com.epam.prykhodko.task1.entity.Product;
import java.util.Locale;

public class NotebookCreator extends ProductCreator {

  public NotebookCreator(InputType inputType, Locale locale) {
    super(inputType, locale);
  }

  @Override
  public Product create() {
    final Notebook notebook = new Notebook((Notebook) super.create());
    setParameters(notebook);
    return notebook;
  }

}
