package com.epam.prykhodko.filter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SearchByDateFilter extends Handler {

  private final Date firstDate;
  private final Date lastDate;


  public SearchByDateFilter(Date firstDate, Date lastDate, String directory) {
    super(new File(directory));
    this.firstDate = firstDate;
    this.lastDate = lastDate;
  }

  @Override
  protected List<String> findFiles(File directory) {
    return findByDate(directory, firstDate, lastDate);
  }

  @Override
  protected List<String> findFiles(List<String> paths) {
    return findByDate(paths, firstDate, lastDate);
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
