package com.epam.prykhodko.entity.thread;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.epam.prykhodko.util.FindSimpleNumbersUtil;
import org.junit.jupiter.api.Test;

class FindSimpleNumbersThreadTest {

  @Test
  void runShouldFindNumbers() {
    new FindSimpleNumbersThread(1,100);
    assertNotNull(FindSimpleNumbersUtil.get());
  }
}