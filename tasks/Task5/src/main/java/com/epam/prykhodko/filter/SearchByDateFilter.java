package com.epam.prykhodko.filter;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SearchByDateFilter extends Handler {

  private Date firstDate;
  private Date lastDate;


  public SearchByDateFilter(Date firstDate, Date lastDate, String directory) {
    this.firstDate = firstDate;
    this.lastDate = lastDate;
    super.directory = new File(directory);
  }

  @Override
  public boolean check() {
    List<String> paths;
    paths = findByDate(directory, firstDate, lastDate);
    if (paths.isEmpty()) {
      return false;
    }
    return checkNext(paths, directory);
  }

  @Override
  public boolean check(List<String> paths, File directory) {
    List<String> tempPaths;
    tempPaths = findByDate(paths, firstDate, lastDate);
    if (tempPaths.isEmpty()) {
      return false;
    }
    return checkNext(tempPaths, directory);
  }

  private List<String> findByDate(File directory, Date firstDate, Date lastDate) {
    List<String> pathsFilteredByDate = new ArrayList<>();
    File[] list = directory.listFiles();

    if (list == null) {
      return Collections.emptyList();
    }

    for (File file : list) {
      if (file.isDirectory()) {
        pathsFilteredByDate.addAll(findByDate(file, firstDate, lastDate));
      } else {
        long modDate = file.lastModified();
        if (modDate > firstDate.getTime() && modDate < lastDate.getTime()) {
          pathsFilteredByDate.add(file.getAbsolutePath());
        }
      }
    }
    return pathsFilteredByDate;
  }


  private List<String> findByDate(List<String> paths, Date firstDate, Date lastDate) {
    List<String> pathsFilteredByDate = new ArrayList<>();
    for (String path : paths) {
      long modDate = new File(path).lastModified();
      if (modDate > firstDate.getTime() && modDate < lastDate.getTime()) {
        pathsFilteredByDate.add(path);
      }
    }
    return pathsFilteredByDate;
  }
}
