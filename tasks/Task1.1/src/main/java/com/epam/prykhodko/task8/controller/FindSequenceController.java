package com.epam.prykhodko.task8.controller;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;
import static com.epam.prykhodko.constant.Constants.STOP_STRING;

import com.epam.prykhodko.task8.util.FindSequence;
import com.epam.prykhodko.util.readers.ConsoleHelper;
import com.epam.prykhodko.util.readers.FileReadWrapper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class FindSequenceController {

  private static final Logger LOGGER = Logger.getLogger(FindSequenceController.class);

  public static void main(String[] args) {
    BasicConfigurator.configure();
    String name;
    while (true) {
      try {
        LOGGER.info("Enter file name, to stop enter \"stop\": ");
        name = ConsoleHelper.readLine();
        if (STOP_STRING.equals(name)) {
          break;
        }
        FileReadWrapper fileReadWrapper = new FileReadWrapper(name);
        String content = fileReadWrapper.readFile();
        FindSequence findSequence = new FindSequence(content);
        ExecutorService service = Executors.newSingleThreadExecutor();

        while (!service.isTerminated()) {
          LOGGER.info(findSequence.getSequence().length());
          Thread.sleep(50);
          service.shutdown();
        }
      } catch (Exception e) {
        LOGGER.error(INCORRECT_INPUT);
      }
    }
  }
}
