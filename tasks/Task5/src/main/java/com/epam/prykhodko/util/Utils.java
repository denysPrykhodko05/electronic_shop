package com.epam.prykhodko.util;

import java.io.File;
import java.util.List;

public class Utils {

  private static List<String> paths;

  public static boolean findFileByName(String name, File file) {
    boolean flag = false;
    File[] list = file.listFiles();
    if (list != null) {
      for (File fil : list) {
        if (fil.isDirectory()) {
          findFileByName(name, fil);
        } else {
          String foundName = fil.getName().split("\\.")[0];
          if (name.equalsIgnoreCase(foundName)) {
            flag = true;
            System.out.println(fil.getAbsolutePath());
            paths.add(foundName);
          }
        }
      }
    }
    return flag;
  }

  public static boolean findFileByExtension(String extension, File file){
    boolean flag =false;
    File[] list = file.listFiles();
    if (list != null) {
      for (File fil : list) {
        if (fil.isDirectory()) {
          findFileByExtension(extension, fil);
        } else {
          String foundExtension = fil.getName().split("\\.")[1];
          if (extension.equalsIgnoreCase(foundExtension)) {
            flag=true;
            System.out.println(fil.getAbsolutePath());
            paths.add(foundExtension);
          }
        }
      }
    }
    return flag;
  }

}
