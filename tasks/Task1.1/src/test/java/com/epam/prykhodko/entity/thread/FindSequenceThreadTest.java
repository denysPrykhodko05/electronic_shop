package com.epam.prykhodko.entity.thread;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.prykhodko.task8.util.FindSequence;
import org.junit.jupiter.api.Test;

class FindSequenceThreadTest {

  @Test
  void runSequenceLengthShouldBeEqualThree() {
    FindSequence findSequence = new FindSequence("zzz zz zzz");
    int actual = findSequence.getSequence().length();
    int expected = 3;
    assertEquals(expected,actual);
  }
}