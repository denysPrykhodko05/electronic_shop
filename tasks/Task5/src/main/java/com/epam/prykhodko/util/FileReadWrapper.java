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

  public void readFile() {
    /*
    int amountOfLine = 0;
    try (BufferedReader bf = new BufferedReader(new FileReader(name));
        Scanner scanner = new Scanner(new FileReader(name))) {
      while (scanner.hasNextLine()) {
        amountOfLine++;
        scanner.next();
      }
      for (int i = 0; i < amountOfLine; i++) {
        System.out.println(bf.readLine());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }*/
  }

  @Override
  public Iterator iterator() {
    return new IteratorImpl(name);
  }

  class IteratorImpl implements Iterator {

    private Scanner scanner;

    IteratorImpl(String file) {
      try {
        scanner = new Scanner(new FileReader(name));
      } catch (FileNotFoundException e) {
        System.out.println("Error. Cannot open file.");
      }
    }


    @Override
    public boolean hasNext() {
        if (scanner.hasNextLine()) {
          return true;
        }
      return false;
    }

    @Override
    public Object next() {
        return scanner.nextLine();
    }

    @Override
    public void remove() {

    }
  }
}
