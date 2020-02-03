package task8.com.epam.prykhodko.entity;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class FindSimpleNumbersOwnCollectionThread extends Thread implements
    Callable<List<Integer>> {

  private int leftBoarder;
  private int rightBoarder;
  private int amountOfThreads;
  private List<Integer> list = new ArrayList<>();

  public FindSimpleNumbersOwnCollectionThread(int leftBoarder, int rightBoarder,
      int amountOfThreads) {
    this.leftBoarder = leftBoarder;
    this.rightBoarder = rightBoarder;
    this.amountOfThreads = amountOfThreads;
  }

  private boolean isPrime(int number) {
    if (number < 2) {
      return true;
    }
    for (int j = 2; j <= number / 2; j++) {
      if ((number % j) == INTEGER_ZERO) {
        return false;
      }
    }
    return true;
  }

  public List<Integer> getResult() {
    return list;
  }

  @Override
  public void run() {
    for (int i = leftBoarder; i <= rightBoarder; i += amountOfThreads) {
      if (isPrime(i)) {
        list.add(i);
      }
    }
  }

  @Override
  public List<Integer> call() {
    for (int i = leftBoarder; i <= rightBoarder; i += amountOfThreads) {
      if (isPrime(i)) {
        list.add(i);
      }
    }
    return list;
  }
}
