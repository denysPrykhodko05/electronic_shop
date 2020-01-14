package com.epam.prykhodko.controller;


import static com.epam.prykhodko.constant.Constants.CHOOSE_DATE_FILTER_STRING;
import static com.epam.prykhodko.constant.Constants.CHOOSE_EXTENSION_FILTER_STRING;
import static com.epam.prykhodko.constant.Constants.CHOOSE_NAME_FILTER_STRING;
import static com.epam.prykhodko.constant.Constants.CHOOSE_SIZE_FILTER_STRING;
import static com.epam.prykhodko.constant.Constants.EMPTY_STRING;
import static com.epam.prykhodko.constant.Constants.ENTER_DIRECTORY_NAME_STRING;
import static com.epam.prykhodko.constant.Constants.ENTER_EXTENSION_STRING;
import static com.epam.prykhodko.constant.Constants.ENTER_FILE_NAME_STRING;
import static com.epam.prykhodko.constant.Constants.ENTER_FIRST_DATE_STRING;
import static com.epam.prykhodko.constant.Constants.ENTER_LAST_DATE_STRING;
import static com.epam.prykhodko.constant.Constants.ENTER_MAX_SIZE_STRING;
import static com.epam.prykhodko.constant.Constants.ENTER_MIN_SIZE_STRING;
import static com.epam.prykhodko.constant.Constants.FILE_NOT_FOUND_ERROR;
import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;
import static com.epam.prykhodko.constant.Constants.REG_MATCH_EXTENSION;
import static com.epam.prykhodko.constant.Constants.STRING_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.epam.prykhodko.filter.FullSearchFilter;
import com.epam.prykhodko.filter.Handler;
import com.epam.prykhodko.filter.SearchByDateFilter;
import com.epam.prykhodko.filter.SearchByFilenameExtensionFilter;
import com.epam.prykhodko.filter.SearchByNameFilter;
import com.epam.prykhodko.filter.SearchBySizeFilter;
import com.epam.prykhodko.util.ConsoleScanner;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DemoPart2 {

  private static Handler handler = null;
  private static String directory = null;

  //C:\\task1\\git pracrice I\\pre_prod_java_q4q1_2019
  public static void main(String[] args) throws ParseException {
    List<Handler> handlerList = new ArrayList<>();

    directory = enterDirectory();

    handlerList.add(nameFilter(directory));
    handlerList.add(extensionFilter(directory));
    handlerList.add(sizeFilter(directory));
    handlerList.add(dateFilter(directory));

    handler = linkFilters(
        Arrays.asList(handlerList.stream().filter(Objects::nonNull).toArray(Handler[]::new)));
    handler.check();
  }


  private static Handler linkFilters(List<Handler> handlers) {
    Handler handler;
    if (handlers.isEmpty()) {
      handler = new FullSearchFilter(directory);
      return handler;
    }
    Handler first = handlers.get(0);
    Handler last;
    handler = first;
    last = first;
    for (int i = 1; i < handlers.size(); i++) {
      last.linkWith(handlers.get(i));
      last = handlers.get(i);
    }
    return handler;
  }

  private static String enterDirectory() {
    String directory = null;
    System.out.println(ENTER_DIRECTORY_NAME_STRING);
    while (directory == null) {
      directory = ConsoleScanner.readLine();
      File directoryFile = new File(directory);
      if (!directoryFile.exists()) {
        directory = null;
        System.out.println(FILE_NOT_FOUND_ERROR);
      }
    }
    return directory;
  }

  private static Handler fullSearch(String directory) {
    return new FullSearchFilter(directory);
  }

  private static Handler nameFilter(String directory) {
    String nameChoose;
    String name = EMPTY_STRING;
    System.out.println(CHOOSE_NAME_FILTER_STRING);
    nameChoose = ConsoleScanner.readLine();

    if (!nameChoose.equals(STRING_ONE)) {
      return null;
    }
    Handler handler = null;
    while (name.isEmpty()) {
      System.out.println(ENTER_FILE_NAME_STRING);
      name = ConsoleScanner.readLine();
      if (name.matches(REG_MATCH_EXTENSION)) {
        name = EMPTY_STRING;
        System.out.println(INCORRECT_INPUT);
        continue;
      }
      handler = new SearchByNameFilter(name, directory);
      return handler;
    }
    return handler;
  }

  private static Handler extensionFilter(String directory) {
    String extension = EMPTY_STRING;
    String extensionChoose = EMPTY_STRING;
    System.out.println(CHOOSE_EXTENSION_FILTER_STRING);
    extensionChoose = ConsoleScanner.readLine();
    if (!extensionChoose.equals(STRING_ONE)) {
      return null;
    }
    Handler handler = null;
    while (extension.isEmpty()) {
      System.out.println(ENTER_EXTENSION_STRING);
      extension = ConsoleScanner.readLine();
      if (extension.matches(REG_MATCH_EXTENSION)) {
        extension = EMPTY_STRING;
        continue;
      }
      handler = new SearchByFilenameExtensionFilter(extension, directory);
      return handler;
    }
    return handler;
  }

  private static Handler sizeFilter(String directory) {
    String sizeChoose = EMPTY_STRING;
    int minSize = INTEGER_MINUS_ONE;
    int maxSize = INTEGER_MINUS_ONE;
    System.out.println(CHOOSE_SIZE_FILTER_STRING);
    sizeChoose = ConsoleScanner.readLine();
    if (!sizeChoose.equals(STRING_ONE)) {
      return null;
    }
    Handler handler = null;
    while (true) {
      try {
        System.out.println(ENTER_MIN_SIZE_STRING);
        minSize = ConsoleScanner.readInt();
        System.out.println(ENTER_MAX_SIZE_STRING);
        maxSize = ConsoleScanner.readInt();
        if (maxSize <= INTEGER_ZERO || minSize <= INTEGER_ZERO) {
          System.out.println(INCORRECT_INPUT);
          continue;
        }
        handler = new SearchBySizeFilter(minSize, maxSize, directory);
        return handler;
      } catch (NumberFormatException e) {
        System.out.println(INCORRECT_INPUT);
      }
    }

  }

  private static Handler dateFilter(String directory) {
    String dateChoose = EMPTY_STRING;
    Date firstDate;
    Date lastDate;
    System.out.println(CHOOSE_DATE_FILTER_STRING);
    dateChoose = ConsoleScanner.readLine();
    if (!dateChoose.equals(STRING_ONE)) {
      return null;
    }
    Handler handler = null;
    while (true) {
      try {
        System.out.println(ENTER_FIRST_DATE_STRING);
        firstDate = ConsoleScanner.readDate();
        System.out.println(ENTER_LAST_DATE_STRING);
        lastDate = ConsoleScanner.readDate();
      } catch (ParseException e) {
        System.out.println(INCORRECT_INPUT);
        continue;
      }
      if (firstDate.getTime() - System.currentTimeMillis() > INTEGER_ZERO
          || lastDate.getTime() - System.currentTimeMillis() > INTEGER_ZERO) {
        System.out.println(INCORRECT_INPUT);
        continue;
      }
      handler = new SearchByDateFilter(firstDate, lastDate, directory);
      return handler;
    }
  }
}
