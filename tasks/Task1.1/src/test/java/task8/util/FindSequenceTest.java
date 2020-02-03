package task8.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindSequenceTest {

  AtomicInteger length = new AtomicInteger(0);
  Object monitor = new Object();
  FindSequence findSequence;

  @BeforeEach
  void init() {
    findSequence = new FindSequence(length, monitor);
  }

  @Test
  void run() {
    Thread thread = new Thread(findSequence);
    thread.run();
    findSequence.setContent("as z as");
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      //TODO
    }
    synchronized (monitor) {
      monitor.notify();
    }
    findSequence.setQuite("stop");

    int expected = 2;
    int actual = findSequence.getFinalLength();
    assertEquals(expected, actual);
  }

  @Test
  void getFinishShouldReturnFalse() {
    assertFalse(findSequence.getFinish());
  }

  @Test
  void setFinishShouldReturnTrue() {
    findSequence.setFinish(true);
    assertTrue(findSequence.getFinish());
  }

  @Test
  void setContent() {
    findSequence.setContent("a");
  }

  @Test
  void setQuite() {
    findSequence.setQuite("stop");
  }

  @Test
  void testToString() {
    assertEquals("length=0, startPosition=0, endPosition=0", findSequence.toString());
  }
}