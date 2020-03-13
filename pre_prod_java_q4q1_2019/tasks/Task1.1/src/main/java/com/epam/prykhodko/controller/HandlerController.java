package com.epam.prykhodko.controller;

import static com.epam.prykhodko.constant.Constants.CHOOSE_DATE_FILTER_STRING;
import static com.epam.prykhodko.constant.Constants.CHOOSE_EXTENSION_FILTER_STRING;
import static com.epam.prykhodko.constant.Constants.CHOOSE_NAME_FILTER_STRING;
import static com.epam.prykhodko.constant.Constants.CHOOSE_SIZE_FILTER_STRING;
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
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.epam.prykhodko.filter.FullSearchFilter;
import com.epam.prykhodko.filter.Handler;
import com.epam.prykhodko.filter.SearchByDateFilter;
import com.epam.prykhodko.filter.SearchByFilenameExtensionFilter;
import com.epam.prykhodko.filter.SearchByNameFilter;
import com.epam.prykhodko.filter.SearchBySizeFilter;
import com.epam.prykhodko.util.readers.ConsoleScanner;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class HandlerController {

  public static Handler handler;
  public static String directory;

  public static void main(String[] args) throws ParseException {
    HandlerController start = new HandlerController();
    start.startFindFiles();
  }

  private Handler linkFilters(List<Handler> handlers) {
    Handler handler;
    if (handlers.isEmpty()) {
      handler = fullSearch(directory);
      return handler;
    }
    Handler first = handlers.get(INTEGER_ZERO);
    Handler last;
    handler = first;
    last = first;
    for (int i = 1; i < handlers.size(); i++) {
      last.linkWith(handlers.get(i));
      last = handlers.get(i);
    }
    return handler;
  }

  private String enterDirectory() {
    String directory = StringUtils.EMPTY;
    System.out.println(ENTER_DIRECTORY_NAME_STRING);
    while (directory.isEmpty()) {
      directory = ConsoleScanner.readLine();
      File directoryFile = new File(directory);
      if (!directoryFile.exists()) {
        directory = null;
        System.out.println(FILE_NOT_FOUND_ERROR);
      }
    }
    return directory;
  }

  private Handler fullSearch(String directory) {
    return new FullSearchFilter(directory);
  }

  private Handler nameFilter(String directory) {
    String nameChoose;
    System.out.println(CHOOSE_NAME_FILTER_STRING);
    nameChoose = ConsoleScanner.readLine();

    if (!nameChoose.equals(STRING_ONE)) {
      return null;
    }
    Handler handler = null;
    String name = StringUtils.EMPTY;
    while (name.isEmpty()) {
      System.out.println(ENTER_FILE_NAME_STRING);
      name = ConsoleScanner.readLine();
      if (name.matches(REG_MATCH_EXTENSION)) {
        name = StringUtils.EMPTY;
        System.out.println(INCORRECT_INPUT);
        continue;
      }
      handler = new SearchByNameFilter(name, directory);
      return handler;
    }
    return handler;
  }

  private Handler extensionFilter(String directory) {
    String extensionChoose;
    System.out.println(CHOOSE_EXTENSION_FILTER_STRING);
    extensionChoose = ConsoleScanner.readLine();
    if (!extensionChoose.equals(STRING_ONE)) {
      return null;
    }
    Handler handler = null;
    String extension = StringUtils.EMPTY;
    while (extension.isEmpty()) {
      System.out.println(ENTER_EXTENSION_STRING);
      extension = ConsoleScanner.readLine();
      if (extension.matches(REG_MATCH_EXTENSION)) {
        extension = StringUtils.EMPTY;
        continue;
      }
      handler = new SearchByFilenameExtensionFilter(extension, directory);
      return handler;
    }
    return handler;
  }

  private Handler sizeFilter(String directory) {
    String sizeChoose;
    int minSize;
    int maxSize;
    System.out.println(CHOOSE_SIZE_FILTER_STRING);
    sizeChoose = ConsoleScanner.readLine();
    if (!sizeChoose.equals(STRING_ONE)) {
      return null;
    }
    Handler handler;
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

  private Handler dateFilter(String directory) {
    String dateChoose;
    Date firstDate;
    Date lastDate;
    System.out.println(CHOOSE_DATE_FILTER_STRING);
    dateChoose = ConsoleScanner.readLine();
    if (!dateChoose.equals(STRING_ONE)) {
      return null;
    }
    Handler handler;
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

  public void startFindFiles() {
    List<Handler> handlerList = new ArrayList<>();
    directory = enterDirectory();
    handlerList.add(nameFilter(directory));
    handlerList.add(extensionFilter(directory));
    handlerList.add(sizeFilter(directory));
    handlerList.add(dateFilter(directory));

    handlerList = handlerList
        .stream()
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
    handler = linkFilters(handlerList);
    handler.check();
  }
}