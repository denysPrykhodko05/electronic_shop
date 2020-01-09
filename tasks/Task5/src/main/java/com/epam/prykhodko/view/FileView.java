package com.epam.prykhodko.view;

import com.epam.prykhodko.util.FileReadWrapper;

public class FileView {

  public static void main(String[] args) {
    FileReadWrapper fileReadWrapper = new FileReadWrapper("1.txt");
   // fileReadWrapper.readFile();
    for (Object str:fileReadWrapper) {
      System.out.println(str);
    }
  }
}