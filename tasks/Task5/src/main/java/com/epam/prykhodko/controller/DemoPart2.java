package com.epam.prykhodko.controller;


import static org.apache.commons.lang3.math.NumberUtils.INTEGER_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
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
import java.util.Date;

public class DemoPart2 {

  public static Handler link(Handler chain, Handler filter) {
    if (chain != null) {
      chain.linkWith(filter);
      return chain;
    }
    return filter;
  }

  public static void main(String[] args) throws ParseException {
//private static String directory = "C:\\task1\\git pracrice I\\pre_prod_java_q4q1_2019";
    String directory = null;
    Handler handler = null;
    int nameChoose = INTEGER_MINUS_ONE;
    int extensionChoose = INTEGER_MINUS_ONE;
    int sizeChoose = INTEGER_MINUS_ONE;
    int dateChoose = INTEGER_MINUS_ONE;
    String name = null;
    String extension = null;
    int minSize = INTEGER_MINUS_ONE;
    int maxSize = INTEGER_MINUS_ONE;
    Date firstDate = null;
    Date lastDate = null;

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

    while (nameChoose != INTEGER_ONE || name == null) {
      try {
        System.out.println("Do you want search by name? 0/1");
        nameChoose = ConsoleHelper.readInt();
        if (nameChoose == INTEGER_ONE) {
          System.out.println("Enter the file name: ");
          name = ConsoleHelper.readLine();
          handler = new SearchByNameFilter(name, directory);
        }
        if (nameChoose == INTEGER_ZERO) {
          break;
        }
      } catch (IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }
    }

    while (extensionChoose == INTEGER_MINUS_ONE || extension == null) {
      try {
        System.out.println("Do you want search by extension? 0/1");
        extensionChoose = ConsoleHelper.readInt();
        if (extensionChoose == INTEGER_ONE) {
          System.out.println("Enter the file extension");
          extension = ConsoleHelper.readLine();
          handler = link(handler, new SearchByFilenameExtensionFilter(extension, directory));
        }
        if (extensionChoose == INTEGER_ZERO) {
          break;
        }
      } catch (IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }
    }

    while (sizeChoose != INTEGER_ONE || minSize <= INTEGER_ONE || maxSize <= INTEGER_ONE) {
      try {
        System.out.println("Do you want search by size? 0/1");
        sizeChoose = ConsoleHelper.readInt();
        if (sizeChoose == INTEGER_ONE) {
          System.out.println("Enter the min size: ");
          minSize = ConsoleHelper.readInt();
          System.out.println("Enter the max size: ");
          maxSize = ConsoleHelper.readInt();
          handler = link(handler, new SearchBySizeFilter(minSize, maxSize, directory));
        }
        if (sizeChoose == INTEGER_ZERO) {
          break;
        }
      } catch (IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }
    }

    while (dateChoose != INTEGER_ONE || firstDate == null || lastDate == null) {
      try {
        System.out.println("Do you want search by date? 0/1");
        dateChoose = ConsoleHelper.readInt();
        if (dateChoose == INTEGER_ONE) {
          System.out.println("Enter the first date(dd/mm/yyyy hh:mm): ");
          firstDate = ConsoleHelper.readDate();
          System.out.println("Enter the last date(dd/mm/yyyy hh:mm): ");
          lastDate = ConsoleHelper.readDate();
          if (firstDate.getTime() - System.currentTimeMillis() > 0
              || lastDate.getTime() - System.currentTimeMillis() > 0) {
            firstDate = null;
            lastDate = null;
            System.out.println("Incorrect input. Try again!!!");
            continue;
          }
          handler = link(handler, new SearchByDateFilter(firstDate, lastDate, directory));
        }
        if (dateChoose == INTEGER_ZERO) {
          break;
        }
      } catch (IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }
    }
    if (handler != null) {
      handler.check();
    }
  }
}
