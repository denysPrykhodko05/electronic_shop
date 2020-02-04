package task8.com.epam.prykhodko.util;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

public class FindPrimeNumbersUtil {

  public static boolean isPrime(int number) {
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
}
