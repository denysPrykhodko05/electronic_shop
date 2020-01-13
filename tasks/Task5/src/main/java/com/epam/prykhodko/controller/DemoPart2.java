package com.epam.prykhodko.controller;


import static org.apache.commons.lang3.math.NumberUtils.INTEGER_MINUS_ONE;
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

  private static Handler last;

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
    String nameChoose="";
    String extensionChoose="";
    String sizeChoose ="";
    String dateChoose ="";
    String name ="";
    String extension ="";
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

    while (name.equals("") || !nameChoose.equals("1")) {
      try {
        System.out.println("Do you want search by name? 0/1");
        nameChoose = ConsoleHelper.readLine();
        if (nameChoose.equalsIgnoreCase("1")) {
          System.out.println("Enter the file name: ");
          name = ConsoleHelper.readLine();
          handler = link(handler, new SearchByNameFilter(name, directory));
        }
        if (nameChoose.equals("0")) {
          break;
        }
      } catch (IOException e) {
        name="";
        nameChoose="";
        System.out.println("Incorrect input. Try again!!!");
      }
    }

    while (extension.equals("")) {
      try {
        System.out.println("Do you want search by extension? 0/1");
        extensionChoose = ConsoleHelper.readLine();
        if (extensionChoose.equalsIgnoreCase("1")) {
          System.out.println("Enter the file extension");
          extension = ConsoleHelper.readLine();
          handler = link(handler, new SearchByFilenameExtensionFilter(extension, directory));
        }
        if (extensionChoose.equalsIgnoreCase("0")) {
          break;
        }
      } catch (IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }
    }

    while (true) {
      try {
        System.out.println("Do you want search by size? 0/1");
        sizeChoose = ConsoleHelper.readLine();
        if (sizeChoose.equalsIgnoreCase("1")) {
          System.out.println("Enter the min size: ");
          minSize = ConsoleHelper.readInt();
          System.out.println("Enter the max size: ");
          maxSize = ConsoleHelper.readInt();
          if (maxSize<=INTEGER_ZERO || minSize <= INTEGER_ZERO){
            continue;
          }
          handler = link(handler, new SearchBySizeFilter(minSize, maxSize, directory));
          break;
        }
        if (sizeChoose.equalsIgnoreCase("0")) {
          break;
        }
        sizeChoose=null;
      } catch (IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }
    }

    while (true) {
      try {
        System.out.println("Do you want search by date? 0/1");
        dateChoose = ConsoleHelper.readLine();
        if (dateChoose.equalsIgnoreCase("1")) {
          System.out.println("Enter the first date(dd/mm/yyyy hh:mm): ");
          firstDate = ConsoleHelper.readDate();
          System.out.println("Enter the last date(dd/mm/yyyy hh:mm): ");
          lastDate = ConsoleHelper.readDate();
          if (firstDate.getTime() - System.currentTimeMillis() > 0
              || lastDate.getTime() - System.currentTimeMillis() > 0) {
            System.out.println("Incorrect input. Try again!!!");
            continue;
          }
          handler = link(handler, new SearchByDateFilter(firstDate, lastDate, directory));
          break;
        }
        if (dateChoose.equalsIgnoreCase("0")) {
          break;
        }
        dateChoose=null;
      } catch (IOException e) {
        System.out.println("Incorrect input. Try again!!!");
      }
    }
    if (handler != null) {
      handler.check();
    }
  }
}
