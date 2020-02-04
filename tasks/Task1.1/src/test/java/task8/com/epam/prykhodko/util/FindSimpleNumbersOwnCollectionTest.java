package task8.com.epam.prykhodko.util;

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
  void findShouldFindFourElements() {
    findSimpleNumbersOwnCollection.find(1, 10, 2);

    int actual = findSimpleNumbersOwnCollection.getList().size();
    int expected = 4;
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void findByExecutorShouldFindFourElements() {
    findSimpleNumbersOwnCollection.findByExecutor(1, 10, 2);

    int actual = findSimpleNumbersOwnCollection.getList().size();
    int expected = 4;
    Assertions.assertEquals(expected, actual);
  }
}