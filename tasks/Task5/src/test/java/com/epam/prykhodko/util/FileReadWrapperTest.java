package com.epam.prykhodko.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FileReadWrapperTest {

  @Test
  void readFileShouldReturnString() {
    FileReadWrapper fileReadWrapper = new FileReadWrapper("C:\\task1\\git pracrice I\\pre_prod_java_q4q1_2019\\tasks\\1.txt");
    assertNotNull(fileReadWrapper.readFile());
  }

  @Test
  void readFileShouldThrowNullPointerException(){

    FileReadWrapper fileReadWrapper = new FileReadWrapper("C:\\task1\\git pracrice I\\pre_prod_java_q4q1_2019\\tasks\\2.txt");
    assertThrows(NullPointerException.class, fileReadWrapper::readFile);
  }
}