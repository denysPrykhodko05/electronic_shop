package task8.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindSimpleNumbersOwnCollectionTest {

  FindSimpleNumbersOwnCollection findSimpleNumbersOwnCollection;

  @BeforeEach
  void init() {
    findSimpleNumbersOwnCollection = new FindSimpleNumbersOwnCollection();
  }

  @Test
  void findShouldFindFiveElements() {
    findSimpleNumbersOwnCollection.find(1, 10, 2);

    int actual = findSimpleNumbersOwnCollection.getList().size();
    int expected = 5;
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void findByExecutor() {
    findSimpleNumbersOwnCollection.findByExecutor(1, 10, 2);

    int actual = findSimpleNumbersOwnCollection.getList().size();
    int expected = 5;
    Assertions.assertEquals(expected, actual);
  }
}