package com.epam.prykhodko.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FileReadWrapperTest {

  @Test
  void readFileShouldReturnString() {
    FileReadWrapper fileReadWrapper = new FileReadWrapper("src/test/resource/1.txt");
    assertNotNull(fileReadWrapper.readFile());
  }

  @Test
  void readFileShouldThrowNullPointerException(){

    FileReadWrapper fileReadWrapper = new FileReadWrapper("2.txt");
    assertThrows(NullPointerException.class, fileReadWrapper::readFile);
  }
}