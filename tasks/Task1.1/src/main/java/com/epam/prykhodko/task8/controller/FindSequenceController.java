package com.epam.prykhodko.task8.controller;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;
import static com.epam.prykhodko.constant.Constants.STOP_STRING;
import static com.epam.prykhodko.constant.Constants.THREAD_INTERRUPTED;

import com.epam.prykhodko.task8.util.FindSequence;
import com.epam.prykhodko.util.readers.ConsoleHelper;
import com.epam.prykhodko.util.readers.FileReadWrapper;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class FindSequenceController {

  private static final Logger LOGGER = Logger.getLogger(FindSequenceController.class);
  private static final Object monitor = new Object();
  private static AtomicInteger length = new AtomicInteger(0);
  private static AtomicInteger localeLength = new AtomicInteger(0);
  private static boolean startFlag = false;

  public static void main(String[] args) {
    BasicConfigurator.configure();
    FindSequence findSequence = new FindSequence(length, monitor);
    ExecutorService service = Executors.newSingleThreadExecutor();
    String name;
    while (true) {
      LOGGER.info("Enter file name, to stop enter \"stop\": ");
      try {
        name = ConsoleHelper.readLine();
        if (STOP_STRING.equals(name)) {
          findSequence.setQuite(name);
          synchronized (monitor) {
            monitor.notify();
          }
          break;
        }
        FileReadWrapper fileReadWrapper = new FileReadWrapper(name);
        String content = fileReadWrapper.readFile();
        if (!startFlag) {
          service.submit(findSequence);
          startFlag = true;
        }
        findSequence.setContent(content);
        Thread.sleep(100);
        synchronized (monitor) {
          monitor.notify();
        }
        while (!findSequence.getFinish()) {
          if (localeLength.get() < length.get()) {
            LOGGER.info("Current length: " + length.get());
            localeLength.set(length.get());
          }
        }
      } catch (IOException | NullPointerException e) {
        LOGGER.info(INCORRECT_INPUT);
      } catch (InterruptedException e) {
        LOGGER.error(THREAD_INTERRUPTED);
      }
      length.set(0);
      localeLength.set(0);
      findSequence.setFinish(false);
    }
    service.shutdown();
  }
}