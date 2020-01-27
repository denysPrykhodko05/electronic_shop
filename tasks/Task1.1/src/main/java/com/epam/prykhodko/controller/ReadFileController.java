package com.epam.prykhodko.controller;

import com.epam.prykhodko.util.readers.FileReadWrapper;

public class ReadFileController {

  public static void main(String[] args) {
    FileReadWrapper fileReadWrapper = new FileReadWrapper("1.txt");
    System.out.println(fileReadWrapper.readFile());
  }
}
