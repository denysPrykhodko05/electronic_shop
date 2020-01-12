package com.epam.prykhodko.controller;

import com.epam.prykhodko.util.FileReadWrapper;

public class DemoPart1 {

  public static void main(String[] args) {
    FileReadWrapper fileReadWrapper = new FileReadWrapper("1.txt");
    System.out.println(fileReadWrapper.readFile());
  }
}
