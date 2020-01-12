package com.epam.prykhodko.util;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_TWO;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.epam.prykhodko.filter.Handler;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUtils {

  public static final List<String> pathsFilteredByExtension = new ArrayList<>();
  public static final List<String> pathsFilteredBySize = new ArrayList<>();
  public static final List<String> pathsFilteredByDate = new ArrayList<>();
  private static final List<String> paths = new ArrayList<>();

  public static List<String> findFileByName(String name, File directory) {
    File[] list = directory.listFiles();
    if (list == null) {
      return null;
    }

    for (File file : list) {
      if (file.isDirectory()) {
        findFileByName(name, file);
      } else {
        String foundName = file.getName().split("\\.")[INTEGER_ZERO];
        if (name.equalsIgnoreCase(foundName)) {
          paths.add(file.getAbsolutePath());
        }
      }
    }
    return paths;
  }

  public static List<String> findFileByExtension(String extension, File directory) {

    File[] list = directory.listFiles();
    if (list == null) {
      return null;
    }

    for (File file : list) {
      if (file.isDirectory()) {
        findFileByExtension(extension, file);
      } else {
        String[] foundExtension = file.getName().split("\\.");
        if (foundExtension.length != INTEGER_TWO) {
          continue;
        }
        if (extension.equalsIgnoreCase(foundExtension[INTEGER_ONE])) {
          pathsFilteredByExtension.add(file.getAbsolutePath());
        }
      }
    }
    return pathsFilteredByExtension;
  }

  public static List<String> findFileByExtension(List<String> paths, String extension) {
    for (String path : paths) {
      if (path.split("\\.")[INTEGER_ONE].equalsIgnoreCase(extension)) {
        pathsFilteredByExtension.add(path);
      }
    }
    return pathsFilteredByExtension;
  }

  public static List<String> findBySize(File directory, int minSize, int maxSize) {
    File[] list = directory.listFiles();
    long length = INTEGER_ZERO;

    if (list == null) {
      return null;
    }

    for (File file : list) {
      if (file.isDirectory()) {
        findBySize(file, minSize, maxSize);
      } else {
        length = file.length();
        if (length / 1024 > minSize && length / 1024 < maxSize) {
          pathsFilteredBySize.add(file.getAbsolutePath());
        }
      }
    }
    return pathsFilteredBySize;
  }

  public static List<String> findBySize(List<String> paths, int minSize, int maxSize) {
    for (String path : paths) {
      long length = new File(path).length();
      if (length / 1024 > minSize && length / 1024 < maxSize) {
        pathsFilteredBySize.add(path);
      }
    }
    return pathsFilteredBySize;
  }

  public static List<String> findByDate(File directory, Date firstDate, Date lastDate) {
    File[] list = directory.listFiles();

    if (list == null) {
      return null;
    }

    for (File file : list) {
      if (file.isDirectory()) {
        findByDate(file, firstDate, lastDate);
      } else {
        long modDate = file.lastModified();
        if (modDate > firstDate.getTime() && modDate < lastDate.getTime()) {
          pathsFilteredByDate.add(file.getAbsolutePath());
        }
      }
    }
    return pathsFilteredByDate;
  }


  public static List<String> findByDate(List<String> paths, Date firstDate, Date lastDate) {
    for (String path : paths) {
      long modDate = new File(path).lastModified();
      if (modDate > firstDate.getTime() && modDate < lastDate.getTime()) {
        pathsFilteredByDate.add(path);
      }
    }
    return pathsFilteredByDate;
  }

  public static Handler link(Handler chain, Handler filter) {
    if (chain != null) {
      chain.linkWith(filter);
      return chain;
    }
    return filter;
  }

}
