package com.epam.prykhodko.controller;

import static java.lang.System.lineSeparator;

import com.epam.prykhodko.task8.controller.FindSequenceController;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;

class FindSequenceControllerTest {

  @Test
  void mainShouldFirdtlyThrowExceptionThenEndCorrectlly() {
    String input = "src/test/java/resources/3.txt"
        + lineSeparator()
        + "src/test/java/resources/2.txt"
        + lineSeparator()
        + "stop";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    FindSequenceController.main(new String[]{});
  }
}