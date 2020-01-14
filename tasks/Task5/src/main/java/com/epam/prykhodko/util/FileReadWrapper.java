package com.epam.prykhodko.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

public class FileReadWrapper implements Iterable {

  private String name;

  public FileReadWrapper(String name) {
    this.name = name;
  }

  public String readFile() {
    StringBuilder stringBuilder= new StringBuilder();
    for (Object o : this) {
      stringBuilder.append(o).append(System.lineSeparator());
    }
    return stringBuilder.toString();
  }

  @Override
  public Iterator iterator() {
    return new IteratorImpl(name);
  }

  class IteratorImpl implements Iterator {

    private Scanner scanner;

    IteratorImpl(String file) {
      try {
        scanner = new Scanner(new FileReader(file));
      } catch (FileNotFoundException e) {
        System.err.println("Error. Cannot open file.");
      }
    }


    @Override
    public boolean hasNext() {
      if (scanner.hasNextLine()) {
        return true;
      }
      scanner.close();
      return false;
    }

    @Override
    public Object next() {
      return scanner.nextLine();
    }
  }
}
