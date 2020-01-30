package com.epam.prykhodko.controller;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.entity.thread.FindSequenceThread;
import com.epam.prykhodko.util.FindSequenceUtil;
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
    String name = null;
    while (!"stop".equals(name)) {
      try {
        LOGGER.info("Enter file name, to stop enter \"stop\": ");
        name = ConsoleHelper.readLine();
        FileReadWrapper fileReadWrapper = new FileReadWrapper(name);
        String content = fileReadWrapper.readFile();
        FindSequenceUtil findSequenceUtil = new FindSequenceUtil(content);
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(new FindSequenceThread(findSequenceUtil));
        while (!service.isTerminated()) {
          LOGGER.info(findSequenceUtil.getSequence().length());
          Thread.sleep(50);
          service.shutdown();
        }
      } catch (Exception e) {
        LOGGER.info(INCORRECT_INPUT);
      }
    }
  }
}
