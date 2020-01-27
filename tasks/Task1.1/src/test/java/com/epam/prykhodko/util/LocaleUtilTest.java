package com.epam.prykhodko.util;

import static com.epam.prykhodko.constant.Constants.STRING_ONE;
import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;

class LocaleUtilTest {

  @Test
  void getLocaleShouldReturnObject() {
    ByteArrayInputStream in = new ByteArrayInputStream(STRING_ONE.getBytes());
    System.setIn(in);
    assertNotNull(LocaleUtil.getLocale());
  }

  @Test
  void getLocaleShouldBeNullAtFirstThenShouldReturnObject() {
    String input = "3"+lineSeparator()+"1";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    assertNotNull(LocaleUtil.getLocale());
  }
}