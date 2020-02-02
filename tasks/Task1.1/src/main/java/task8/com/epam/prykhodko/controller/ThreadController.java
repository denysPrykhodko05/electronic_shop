package task8.com.epam.prykhodko.controller;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.util.readers.ConsoleHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import task8.util.FindSimpleNumbersCommonCollection;
import task8.util.FindSimpleNumbersOwnCollection;

public class ThreadController {

  private static final Logger LOGGER = Logger.getLogger(ThreadController.class);

  public static void main(String[] args) {
    BasicConfigurator.configure();
    int min;
    int max;
    int amountOfThreads;
    boolean correctInput = false;
    List<Integer> list = new ArrayList<>();
    FindSimpleNumbersCommonCollection findSimpleNumbersCommonCollection = new FindSimpleNumbersCommonCollection(
        list);
    FindSimpleNumbersOwnCollection findSimpleNumbersOwnCollection = new FindSimpleNumbersOwnCollection();

    while (!correctInput) {
      try {
        LOGGER.info("Enter min range: ");
        min = ConsoleHelper.readInt();

        LOGGER.info("Enter max range: ");
        max = ConsoleHelper.readInt();

        LOGGER.info("Enter amount of threads: ");
        amountOfThreads = ConsoleHelper.readInt();

        if (min >= max || amountOfThreads <= 0) {
          LOGGER.info(INCORRECT_INPUT);
          continue;
        }

        //findSimpleNumbersCommonCollection.find(min, max, amountOfThreads);
        //findSimpleNumbersCommonCollection.findByExecutor(min, max, amountOfThreads);
        //findSimpleNumbersOwnCollection.find(min,max,amountOfThreads);
        findSimpleNumbersOwnCollection.findByExecutor(min,max,amountOfThreads);
        correctInput = true;
      } catch (IOException | NumberFormatException e) {
        LOGGER.info(INCORRECT_INPUT);
      }
    }
  }
}
