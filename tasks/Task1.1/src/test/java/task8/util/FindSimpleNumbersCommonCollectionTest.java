package task8.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindSimpleNumbersCommonCollectionTest {

  FindSimpleNumbersCommonCollection findSimpleNumbersCommonCollection;
  List<Integer> list = new ArrayList<>();

  @BeforeEach
  void init() {
    findSimpleNumbersCommonCollection = new FindSimpleNumbersCommonCollection(list);
  }

  @Test
  void findFirstlyShouldThrowInterruptedThenListSizeShouldBeEqualFive() {
    Thread.currentThread().interrupt();
    findSimpleNumbersCommonCollection.find(1, 10, 2);

    int actual = list.size();
    int expected = 5;

    assertEquals(actual, expected);
  }

  @Test
  void findByExecutorFirstlyShouldThrowInterruptedThenListSizeShouldBeEqualFive() {
    Thread.currentThread().interrupt();
    findSimpleNumbersCommonCollection.findByExecutor(1, 10, 2);

    int actual = list.size();
    int expected = 5;

    assertEquals(actual, expected);
  }

  @Test
  void getListShouldReturnNotNullList(){
    Assertions.assertNotNull(findSimpleNumbersCommonCollection.getList());
  }
}