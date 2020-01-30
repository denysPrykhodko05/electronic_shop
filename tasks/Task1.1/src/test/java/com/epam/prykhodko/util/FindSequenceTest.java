package com.epam.prykhodko.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FindSequenceTest {

  @Test
  void findSequenceShouldFindSequence() {
    FindSequence findSequence = new FindSequence("zzz zz zzz");
    findSequence.findSequence();
    int actual = findSequence.getSequence().length();
    int expected = 3;
    assertEquals(expected,actual);
  }
}