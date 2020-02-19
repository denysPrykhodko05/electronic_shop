package com.epam.prykhodko.controller;

import static java.lang.System.lineSeparator;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;

class ShopViewTask7Test {

  @Test
  void mainShouldWorkWithOutException() {
    String input = "1"
        + lineSeparator() + "1"
        + lineSeparator() + "1"
        + lineSeparator() + "0";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ShopViewTask7.main(new String[]{});
  }

  @Test
  void mainFirstlyShouldThrowExceptionThenEndCorrectly() {
    String input = "1"
        + lineSeparator() + "1"
        + lineSeparator() + "1"
        + lineSeparator() + "asd"
        + lineSeparator() + "0";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ShopViewTask7.main(new String[]{});
  }
}