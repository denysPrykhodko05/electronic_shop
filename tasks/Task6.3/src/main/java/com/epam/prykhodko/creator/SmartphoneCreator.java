package com.epam.prykhodko.creator;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.task1.entity.Smartphone;
import java.io.IOException;
import org.apache.log4j.Logger;

public class SmartphoneCreator extends ProductCreator {

  private final Logger logger = Logger.getLogger(ProductCreator.class);
  private Smartphone smartphone;
  private InputType inputType;
  private boolean correctInputFlag;

  public SmartphoneCreator(InputType inputType, Smartphone smartphone) {
    super(inputType, smartphone);
    this.inputType = inputType;
    this.smartphone = smartphone;
    setParameters();
  }

  private void setParameters() {
    while (!correctInputFlag) {
      try {
        System.out.println("Enter model of touchscreen: ");
        smartphone.setModelOfTouchScreen(inputType.readLine());
        System.out.println("Enter communication standard");
        smartphone.setCommunicationStandard(inputType.readLine());
      } catch (IOException | NumberFormatException e) {
        logger.error(INCORRECT_INPUT);
        continue;
      }
      correctInputFlag = true;
    }
  }
}
