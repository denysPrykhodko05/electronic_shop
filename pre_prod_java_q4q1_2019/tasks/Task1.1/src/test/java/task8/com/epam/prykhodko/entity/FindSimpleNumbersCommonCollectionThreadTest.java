package task8.com.epam.prykhodko.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class FindSimpleNumbersCommonCollectionThreadTest {

  @Test
  void runListShouldContainsFourElements() {
    List<Integer> list = new ArrayList<>();
    new FindSimpleNumbersCommonCollectionThread(list, 1, 10).run();

    int expected = 5;
    int actual = list.size();
    assertEquals(expected, actual);
  }
}