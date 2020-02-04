package task8.com.epam.prykhodko.entity;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.util.List;
import task8.com.epam.prykhodko.util.FindPrimeNumbersUtil;

public class FindSimpleNumbersCommonCollectionThread implements Runnable {

  private int leftBoarder;
  private int rightBoarder;
  private List<Integer> list;

  public FindSimpleNumbersCommonCollectionThread(List<Integer> list, int leftBoarder,
      int rightBoarder) {
    this.list = list;
    this.leftBoarder = leftBoarder;
    this.rightBoarder = rightBoarder;
  }

  private boolean isPrime(int number) {
    if (number <= 2) {
      return true;
    }
    for (int j = 2; j <= number / 2; j++) {
      if ((number % j) == INTEGER_ZERO) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void run() {
    for (int i = leftBoarder; i <= rightBoarder; i++) {
      if (FindPrimeNumbersUtil.isPrime(i)) {
        list.add(i);
      }
    }
  }
}
