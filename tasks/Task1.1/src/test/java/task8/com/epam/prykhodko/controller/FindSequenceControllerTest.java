package task8.com.epam.prykhodko.controller;

import static java.lang.System.lineSeparator;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;

class FindSequenceControllerTest {

  @Test
  void mainShouldFirstlyThrowExceptionThenEndCorrectlly() {
    Thread.currentThread().interrupt();
    String input = "src/test/java/resources/3.txt"
        + lineSeparator()
        + "src/test/java/resources/1.txt"
        + lineSeparator()
        + "stop";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    FindSequenceController.main(new String[]{});
  }
}