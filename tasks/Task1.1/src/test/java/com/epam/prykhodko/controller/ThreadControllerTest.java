package com.epam.prykhodko.controller;

import static java.lang.System.lineSeparator;

import com.epam.prykhodko.task8.controller.ThreadController;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;

class ThreadControllerTest {

  @Test
  void mainShouldThrowExceptionThenEndCorrectly() {
    String input = "1"
        + lineSeparator() + "1"
        + lineSeparator() + "200"
        + lineSeparator() + "asd"
        + lineSeparator() + "1"
        + lineSeparator() + "200"
        + lineSeparator() + "5";

    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ThreadController.main(new String[]{});
  }
}