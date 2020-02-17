package task8.com.epam.prykhodko.util;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FindPrimeNumbersUtilTest {

  List<Integer> list = new ArrayList<>();

  @Test
  void search() {
    FindPrimeNumbersUtil.search(1, 10, list);

    int actual = list.size();
    int expected = 4;

    Assertions.assertEquals(expected, actual);
  }
}