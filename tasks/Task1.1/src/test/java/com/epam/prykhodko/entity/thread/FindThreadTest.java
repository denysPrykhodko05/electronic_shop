package com.epam.prykhodko.entity.thread;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.epam.prykhodko.util.FindSimpleNumbersUtil;
import org.junit.jupiter.api.Test;

class FindThreadTest {

  @Test
  void runShouldFindNumbers() {
    new FindThread(1,100);
    assertNotNull(FindSimpleNumbersUtil.get());
  }
}