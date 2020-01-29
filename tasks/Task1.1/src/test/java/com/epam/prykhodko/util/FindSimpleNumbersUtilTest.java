package com.epam.prykhodko.util;

import static com.epam.prykhodko.constant.Constants.THREAD_INTERRUPTED;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

class FindSimpleNumbersUtilTest {

 private static final Logger LOGGER = Logger.getLogger(FindSimpleNumbersUtil.class);

  @Test
  void findShouldAddToList() {
    FindSimpleNumbersUtil.find(1, 100, 6);
    try {
      Thread.sleep(1000);
      assertNotNull(FindSimpleNumbersUtil.get());
    } catch (InterruptedException e) {
        LOGGER.info(THREAD_INTERRUPTED);
    }
  }
}