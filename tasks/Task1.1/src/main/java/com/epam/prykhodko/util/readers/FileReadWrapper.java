package com.epam.prykhodko.util.readers;

import static com.epam.prykhodko.constant.Constants.FILE_NOT_FOUND_ERROR;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class FileReadWrapper implements Iterable {

  private static final Logger LOGGER = Logger.getLogger(FileReadWrapper.class);
  private String name;

  public FileReadWrapper(String name) {
    this.name = name;
  }

  public String readFile() throws NullPointerException {
    StringBuilder stringBuilder = new StringBuilder();
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
        LOGGER.info(FILE_NOT_FOUND_ERROR);
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
