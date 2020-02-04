package task8.com.epam.prykhodko.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FindPrimeNumbersUtilTest {

  @Test
  void isPrimeShouldReturnTrue() {
    Assertions.assertTrue(FindPrimeNumbersUtil.isPrime(3));
  }

  @Test
  void isPrimeShouldReturnFalse() {
    Assertions.assertFalse(FindPrimeNumbersUtil.isPrime(4));
  }
}