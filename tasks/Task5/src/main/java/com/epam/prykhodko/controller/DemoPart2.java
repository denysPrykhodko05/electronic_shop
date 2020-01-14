package com.epam.prykhodko.controller;


import static com.epam.prykhodko.constant.Constants.EMPTY_STRING;
import static com.epam.prykhodko.constant.Constants.STRING_ONE;
import static com.epam.prykhodko.constant.Constants.STRING_ZERO;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.epam.prykhodko.filter.Handler;
import com.epam.prykhodko.filter.SearchByDateFilter;
import com.epam.prykhodko.filter.SearchByFilenameExtensionFilter;
import com.epam.prykhodko.filter.SearchByNameFilter;
import com.epam.prykhodko.filter.SearchBySizeFilter;
import com.epam.prykhodko.util.ConsoleHelper;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DemoPart2 {

  private static Handler handler = null;

  //C:\\task1\\git pracrice I\\pre_prod_java_q4q1_2019
  public static void main(String[] args) throws ParseException {
    List<Handler> handlerList = new ArrayList<>();
    String directory = null;

    directory = enterDirectory();

    handlerList.add(nameFilter(directory));
    handlerList.add(extensionFilter(directory));
    handlerList.add(sizeFilter(directory));
    handlerList.add(dateFilter(directory));

    linkFilters(
        Arrays.asList(handlerList.stream().filter(Objects::nonNull).toArray(Handler[]::new)));
    handler.check();
  }


  private static void linkFilters(List<Handler> handlers) {
    Handler first = handlers.get(0);
    Handler last;
    handler = first;
    last = first;
    for (int i = 1; i < handlers.size(); i++) {
      last.linkWith(handlers.get(i));
      last = handlers.get(i);
    }
  }

  private static String enterDirectory() {
    String directory = null;
    System.out.println("Enter directory name: ");
    while (directory == null) {
      try {
        directory = ConsoleHelper.readLine();
        File directoryFile = new File(directory);
        if (!directoryFile.exists()) {
          directory = null;
          System.out.println("File with this name doesn't exists. Try again!!!");
        }
      } catch (IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }
    }
    return directory;
  }

  private static Handler nameFilter(String directory) {
    String nameChoose = EMPTY_STRING;
    String name = EMPTY_STRING;
    while (name.isEmpty() || !nameChoose.equals(STRING_ONE)) {
      try {
        System.out.println("Do you want search by name? 0/1");
        nameChoose = ConsoleHelper.readLine();
        if (nameChoose.equalsIgnoreCase(STRING_ONE)) {
          System.out.println("Enter the file name: ");
          name = ConsoleHelper.readLine();
          return new SearchByNameFilter(name, directory);
        }
        if (nameChoose.equals(STRING_ZERO)) {
          return null;
        }
      } catch (IOException e) {
        name = EMPTY_STRING;
        nameChoose = EMPTY_STRING;
        System.out.println("Incorrect input. Try again!!!");
      }
    }
    return null;
  }

  private static Handler extensionFilter(String directory) {
    String extension = EMPTY_STRING;
    String extensionChoose = EMPTY_STRING;
    while (extension.isEmpty() || !extensionChoose.equals(STRING_ONE)) {
      try {
        System.out.println("Do you want search by extension? 0/1");
        extensionChoose = ConsoleHelper.readLine();
        if (extensionChoose.equalsIgnoreCase(STRING_ONE)) {
          System.out.println("Enter the file extension");
          extension = ConsoleHelper.readLine();
          if (extension.matches("(.*)\\.(.*)")) {
            extensionChoose = EMPTY_STRING;
            continue;
          }
          return new SearchByFilenameExtensionFilter(extension, directory);
        }
        if (extensionChoose.equalsIgnoreCase(STRING_ZERO)) {
          return null;
        }
      } catch (IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }
    }
    return null;
  }

  private static Handler sizeFilter(String directory) {
    String sizeChoose = EMPTY_STRING;
    int minSize;
    int maxSize;
    while (!sizeChoose.equals(STRING_ONE)) {
      try {
        System.out.println("Do you want search by size? 0/1");
        sizeChoose = ConsoleHelper.readLine();
        if (sizeChoose.equals("1")) {
          System.out.println("Enter the min size in kilobytes: ");
          minSize = ConsoleHelper.readInt();
          System.out.println("Enter the max size in kilobytes: ");
          maxSize = ConsoleHelper.readInt();
          if (maxSize <= INTEGER_ZERO || minSize <= INTEGER_ZERO) {
            sizeChoose = "";
            continue;
          }
          return new SearchBySizeFilter(minSize, maxSize, directory);
        }
        if (sizeChoose.equals(STRING_ZERO)) {
          return null;
        }
      } catch (IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }
    }
    return null;
  }

  private static Handler dateFilter(String directory) {
    String dateChoose = EMPTY_STRING;
    Date firstDate;
    Date lastDate;
    while (!dateChoose.equals(STRING_ONE)) {
      try {
        System.out.println("Do you want search by date? 0/1");
        dateChoose = ConsoleHelper.readLine();
        if (dateChoose.equalsIgnoreCase(STRING_ONE)) {
          System.out.println("Enter the first date(dd/mm/yyyy hh:mm): ");
          firstDate = ConsoleHelper.readDate();
          System.out.println("Enter the last date(dd/mm/yyyy hh:mm): ");
          lastDate = ConsoleHelper.readDate();
          if (firstDate.getTime() - System.currentTimeMillis() > INTEGER_ZERO
              || lastDate.getTime() - System.currentTimeMillis() > INTEGER_ZERO) {
            System.out.println("Incorrect input. Try again!!!");
            dateChoose = "";
            continue;
          }
          return new SearchByDateFilter(firstDate, lastDate, directory);
        }
        if (dateChoose.equalsIgnoreCase("0")) {
          return null;
        }
      } catch (IOException | ParseException e) {
        System.out.println("Incorrect input. Try again!!!");
      }
    }
    return null;
  }
}
