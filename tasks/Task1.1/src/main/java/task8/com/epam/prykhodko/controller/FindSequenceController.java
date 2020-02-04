package task8.com.epam.prykhodko.controller;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;
import static com.epam.prykhodko.constant.Constants.STOP_STRING;
import static com.epam.prykhodko.constant.Constants.THREAD_INTERRUPTED;

import com.epam.prykhodko.util.readers.ConsoleHelper;
import com.epam.prykhodko.util.readers.FileReadWrapper;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import task8.com.epam.prykhodko.util.FindSequence;

public class FindSequenceController {

  private static final Logger LOGGER = Logger.getLogger(FindSequenceController.class);
  private static final Object monitor = new Object();
  private static int localeLength = 0;
  private static boolean startFlag = false;

  public static void main(String[] args) {
    BasicConfigurator.configure();
    FindSequence findSequence = new FindSequence(monitor);
    ExecutorService service = Executors.newSingleThreadExecutor();
    String name;
    while (true) {
      LOGGER.info("Enter file name, to stop enter \"stop\": ");
      try {
        name = ConsoleHelper.readLine();
        if (STOP_STRING.equals(name)) {
          findSequence.setExit(name);
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
          if (localeLength < findSequence.getLength()) {
            LOGGER.info("Current length: " + findSequence.getLength());
            localeLength = findSequence.getLength();
          }
        }
      } catch (IOException | NullPointerException e) {
        LOGGER.info(INCORRECT_INPUT);
      } catch (InterruptedException e) {
        LOGGER.info(THREAD_INTERRUPTED);
      }
      localeLength = 0;
      findSequence.setFinish(false);
      LOGGER.info(findSequence);
    }
    service.shutdown();
  }
}