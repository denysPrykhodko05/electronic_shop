package com.epam.prykhodko.creator;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.task1.entity.Notebook;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.util.ConsoleHelper;
import java.io.IOException;
import org.apache.log4j.Logger;

public class NotebookCreator extends ProductCreator {

  private static final Logger LOGGER = Logger.getLogger(NotebookCreator.class);
  private boolean correctInputFlag;

  public NotebookCreator(InputType inputType) {
    super(inputType);
    this.inputType = inputType;
  }

  @Override
  public Product create() {
    final Notebook notebook = new Notebook(super.create());
    setParameters(notebook);
    return notebook;
  }

  private void setParameters(Notebook notebook) {
    while (!correctInputFlag) {
      try {
        System.out.println("Enter model of touchpad");
        String model = ConsoleHelper.readLine();
        notebook.setModelOfTouchpad(inputType.readLine(model));
        correctInputFlag = true;
      } catch (IOException | NumberFormatException e) {
        LOGGER.error(INCORRECT_INPUT);
      }
    }
  }
}