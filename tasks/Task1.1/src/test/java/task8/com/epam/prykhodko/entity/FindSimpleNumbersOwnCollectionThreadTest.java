package task8.com.epam.prykhodko.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindSimpleNumbersOwnCollectionThreadTest {

  FindSimpleNumbersOwnCollectionThread findSimpleNumbersOwnCollectionThread;

  @BeforeEach
  void init() {
    findSimpleNumbersOwnCollectionThread = new FindSimpleNumbersOwnCollectionThread(1, 10, 2);
  }

  @Test
  void getResultShouldReturnListSizeEqualFour() {
    findSimpleNumbersOwnCollectionThread.call();

    int actual = findSimpleNumbersOwnCollectionThread.getResult().size();
    int expected = 4;

    assertEquals(expected, actual);
  }

  @Test
  void callShouldReturnListSizeEqualFour() {
    int actual = findSimpleNumbersOwnCollectionThread.call().size();
    int expected = 4;

    assertEquals(expected, actual);
  }
}