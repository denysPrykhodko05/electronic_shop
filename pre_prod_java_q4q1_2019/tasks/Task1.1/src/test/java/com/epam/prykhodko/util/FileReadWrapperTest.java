package com.epam.prykhodko.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epam.prykhodko.util.readers.FileReadWrapper;
import org.junit.jupiter.api.Test;

public class FileReadWrapperTest {

  @Test
  void readFileShouldReturnString() {
    FileReadWrapper fileReadWrapper = new FileReadWrapper("src/test/resources/1.txt");
    assertNotNull(fileReadWrapper.readFile());
  }

  @Test
  void readFileShouldThrowNullPointerException(){

    FileReadWrapper fileReadWrapper = new FileReadWrapper("2.txt");
    assertThrows(NullPointerException.class, fileReadWrapper::readFile);
  }
}