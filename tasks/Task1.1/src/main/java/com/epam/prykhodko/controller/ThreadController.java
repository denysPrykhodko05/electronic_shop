package com.epam.prykhodko.controller;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.util.FindSimpleNumbersUtil;
import com.epam.prykhodko.util.readers.ConsoleHelper;
import java.io.IOException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ThreadController {

  private static final Logger LOGGER = Logger.getLogger(ThreadController.class);

  public static void main(String[] args) {
    BasicConfigurator.configure();
    int min;
    int max;
    int threads;
    boolean correctInput = false;

    while (!correctInput) {
      try {
        LOGGER.info("Enter min range: ");
        min = ConsoleHelper.readInt();

        LOGGER.info("Enter max range: ");
        max = ConsoleHelper.readInt();

        LOGGER.info("Enter amount of threads: ");
        threads = ConsoleHelper.readInt();

        if (min>=max || threads<=0){
          LOGGER.info(INCORRECT_INPUT);
          continue;
        }

        FindSimpleNumbersUtil.find(min, max, threads);
        correctInput = true;
      } catch (IOException | NumberFormatException e) {
        LOGGER.info(INCORRECT_INPUT);
      }
    }
  }
}
