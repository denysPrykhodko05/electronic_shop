package task8.com.epam.prykhodko.util;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.util.List;

public class FindPrimeNumbersUtil {

  public static void search(int leftBoarder, int rightBoarder, List<Integer> list) {
    for (int i = leftBoarder; i <= rightBoarder; i++) {
      if (FindPrimeNumbersUtil.isPrime(i)) {
        list.add(i);
      }
    }
  }

  private static boolean isPrime(int number) {
    if (number < 2) {
      return false;
    }
    for (int j = 2; j <= Math.sqrt(number); j++) {
      if ((number % j) == INTEGER_ZERO) {
        return false;
      }
    }
    return true;
  }
}
