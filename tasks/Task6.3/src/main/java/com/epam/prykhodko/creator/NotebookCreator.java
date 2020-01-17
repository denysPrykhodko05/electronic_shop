package com.epam.prykhodko.creator;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.task1.entity.Notebook;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.util.ConsoleHelper;
import java.io.IOException;
import org.apache.log4j.Logger;

public class NotebookCreator extends ProductCreator {

  private final Logger logger = Logger.getLogger(NotebookCreator.class);
  private Notebook notebook;
  private boolean correctInputFlag;

  public NotebookCreator(InputType inputType, Notebook notebook) {
    super(inputType,notebook);
    this.inputType = inputType;
    this.notebook = notebook;
  }

  @Override
  public Product create() {
    super.create();
    setParameters();
    return notebook;
  }

  private void setParameters() {
    while (!correctInputFlag) {
      try {
        System.out.println("Enter model of touchpad");
        String model = ConsoleHelper.readLine();
        notebook.setModelOfTouchpad(inputType.readLine(model));
      } catch (IOException | NumberFormatException e) {
        logger.error(INCORRECT_INPUT);
        continue;
      }
      correctInputFlag = true;
    }
  }
}