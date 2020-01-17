package com.epam.prykhodko.creator;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.task1.entity.Smartphone;
import com.epam.prykhodko.util.ConsoleHelper;
import java.io.IOException;
import org.apache.log4j.Logger;

public class SmartphoneCreator extends ProductCreator {

  private final Logger logger = Logger.getLogger(ProductCreator.class);
  private Smartphone smartphone;
  private boolean correctInputFlag;

  public SmartphoneCreator(InputType inputType, Smartphone smartphone) {
    super(inputType, smartphone);
    this.inputType = inputType;
    this.smartphone = smartphone;
  }

  @Override
  public Product create() {
    super.create();
    setParameters();
    return smartphone;
  }

  private void setParameters() {
    while (!correctInputFlag) {
      try {
        System.out.println("Enter model of touchscreen: ");
        String touchscreen = ConsoleHelper.readLine();
        smartphone.setModelOfTouchScreen(inputType.readLine(touchscreen));
        System.out.println("Enter communication standard");
        String communication = ConsoleHelper.readLine();
        smartphone.setCommunicationStandard(inputType.readLine(communication));
      } catch (IOException | NumberFormatException e) {
        logger.error(INCORRECT_INPUT);
        continue;
      }
      correctInputFlag = true;
    }
  }
}
