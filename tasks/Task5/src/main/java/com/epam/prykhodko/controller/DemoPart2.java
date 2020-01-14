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
//C:\\task1\\git pracrice I\\pre_prod_java_q4q1_2019
    String directory = null;
    Handler handler = null;
    String nameChoose=EMPTY_STRING;
    String extensionChoose=EMPTY_STRING;
    String sizeChoose =EMPTY_STRING;
    String dateChoose =EMPTY_STRING;
    String name =EMPTY_STRING;
    String extension =EMPTY_STRING;
    int minSize;
    int maxSize;
    Date firstDate;
    Date lastDate ;

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

    while (name.isEmpty() || !nameChoose.equals(STRING_ONE)) {
      try {
        System.out.println("Do you want search by name? 0/1");
        nameChoose = ConsoleHelper.readLine();
        if (nameChoose.equalsIgnoreCase(STRING_ONE)) {
          System.out.println("Enter the file name: ");
          name = ConsoleHelper.readLine();
          handler = link(handler, new SearchByNameFilter(name, directory));
        }
        if (nameChoose.equals(STRING_ZERO)) {
          break;
        }
      } catch (IOException e) {
        name=EMPTY_STRING;
        nameChoose=EMPTY_STRING;
        System.out.println("Incorrect input. Try again!!!");
      }
    }

    while (extension.isEmpty() || !extensionChoose.equals(STRING_ONE)) {
      try {
        System.out.println("Do you want search by extension? 0/1");
        extensionChoose = ConsoleHelper.readLine();
        if (extensionChoose.equalsIgnoreCase(STRING_ONE)) {
          System.out.println("Enter the file extension");
          extension = ConsoleHelper.readLine();
          if (extension.matches("(.*)\\.(.*)")){
            extensionChoose="";
            continue;
          }
          handler = link(handler, new SearchByFilenameExtensionFilter(extension, directory));
        }
        if (extensionChoose.equalsIgnoreCase(STRING_ZERO)) {
          break;
        }
      } catch (IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }
    }

    while (!sizeChoose.equals(STRING_ONE)) {
      try {
        System.out.println("Do you want search by size? 0/1");
        sizeChoose = ConsoleHelper.readLine();
        if (sizeChoose.equals("1")) {
          System.out.println("Enter the min size in kilobytes: ");
          minSize = ConsoleHelper.readInt();
          System.out.println("Enter the max size in kilobytes: ");
          maxSize = ConsoleHelper.readInt();
          if (maxSize<=INTEGER_ZERO || minSize <= INTEGER_ZERO){
            sizeChoose="";
            continue;
          }
          handler = link(handler, new SearchBySizeFilter(minSize, maxSize, directory));
          break;
        }
        if (sizeChoose.equals(STRING_ZERO)) {
          break;
        }
      } catch (IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }
    }

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
            dateChoose="";
            continue;
          }
          handler = link(handler, new SearchByDateFilter(firstDate, lastDate, directory));
          break;
        }
        if (dateChoose.equalsIgnoreCase("0")) {
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
