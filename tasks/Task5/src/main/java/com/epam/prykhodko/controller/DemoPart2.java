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
    System.out.println(ENTER_DIRECTORY_NAME_STRING);
    while (directory == null) {
      try {
        directory = ConsoleHelper.readLine();
        File directoryFile = new File(directory);
        if (!directoryFile.exists()) {
          directory = null;
          System.out.println(FILE_NOT_FOUND_ERROR);
        }
      } catch (IOException e) {
        System.out.println(INCORRECT_INPUT);
      }
    }
    return directory;
  }

  private static Handler nameFilter(String directory) {
    String nameChoose = EMPTY_STRING;
    String name = EMPTY_STRING;
    while (name.isEmpty() || !nameChoose.equals(STRING_ONE)) {
      try {
        System.out.println(CHOOSE_NAME_FILTER_STRING);
        nameChoose = ConsoleHelper.readLine();
        if (nameChoose.equalsIgnoreCase(STRING_ONE)) {
          System.out.println(ENTER_FILE_NAME_STRING);
          name = ConsoleHelper.readLine();
          return new SearchByNameFilter(name, directory);
        }
        if (nameChoose.equals(STRING_ZERO)) {
          return null;
        }
      } catch (IOException e) {
        name = EMPTY_STRING;
        nameChoose = EMPTY_STRING;
        System.out.println(INCORRECT_INPUT);
      }
    }
    return null;
  }

  private static Handler extensionFilter(String directory) {
    String extension = EMPTY_STRING;
    String extensionChoose = EMPTY_STRING;
    while (extension.isEmpty() || !extensionChoose.equals(STRING_ONE)) {
      try {
        System.out.println(CHOOSE_EXTENSION_FILTER_STRING);
        extensionChoose = ConsoleHelper.readLine();
        if (extensionChoose.equalsIgnoreCase(STRING_ONE)) {
          System.out.println(ENTER_EXTENSION_STRING);
          extension = ConsoleHelper.readLine();
          if (extension.matches(REG_MATCH_EXTENSION)) {
            extensionChoose = EMPTY_STRING;
            continue;
          }
          return new SearchByFilenameExtensionFilter(extension, directory);
        }
        if (extensionChoose.equalsIgnoreCase(STRING_ZERO)) {
          return null;
        }
      } catch (IOException e) {
        System.out.println(INCORRECT_INPUT);
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
        System.out.println(CHOOSE_SIZE_FILTER_STRING);
        sizeChoose = ConsoleHelper.readLine();
        if (sizeChoose.equals(STRING_ONE)) {
          System.out.println(ENTER_MIN_SIZE_STRING);
          minSize = ConsoleHelper.readInt();
          System.out.println(ENTER_MAX_SIZE_STRING);
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
        System.out.println(INCORRECT_INPUT);
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
        System.out.println(CHOOSE_DATE_FILTER_STRING);
        dateChoose = ConsoleHelper.readLine();
        if (dateChoose.equalsIgnoreCase(STRING_ONE)) {
          System.out.println(ENTER_FIRST_DATE_STRING);
          firstDate = ConsoleHelper.readDate();
          System.out.println(ENTER_LAST_DATE_STRING);
          lastDate = ConsoleHelper.readDate();
          if (firstDate.getTime() - System.currentTimeMillis() > INTEGER_ZERO
              || lastDate.getTime() - System.currentTimeMillis() > INTEGER_ZERO) {
            System.out.println(INCORRECT_INPUT);
            dateChoose = "";
            continue;
          }
          return new SearchByDateFilter(firstDate, lastDate, directory);
        }
        if (dateChoose.equalsIgnoreCase(STRING_ZERO)) {
          return null;
        }
      } catch (IOException | ParseException e) {
        System.out.println(INCORRECT_INPUT);
      }
    }
    return null;
  }
}
