package com.epam.prykhodko.filter;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchByDateFilter extends Handler {

  private File directory;
  private Date firstDate;
  private Date lastDate;
  private List<String> paths;
  public final List<String> pathsFilteredByDate = new ArrayList<>();

  public SearchByDateFilter(Date firstDate, Date lastDate, String directory) {
    this.firstDate = firstDate;
    this.lastDate = lastDate;
    this.directory = new File(directory);
  }

  @Override
  public boolean check() {
    paths = findByDate(directory, firstDate, lastDate);
    if (this.paths == null) {
      return false;
    }
    return checkNext(paths, directory);
  }

  @Override
  public boolean check(List<String> paths, File directory) {
    this.paths = findByDate(paths, firstDate, lastDate);
    if (this.paths == null) {
      return false;
    }
    return checkNext(this.paths, directory);
  }

  public List<String> findByDate(File directory, Date firstDate, Date lastDate) {
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


  public List<String> findByDate(List<String> paths, Date firstDate, Date lastDate) {
    for (String path : paths) {
      long modDate = new File(path).lastModified();
      if (modDate > firstDate.getTime() && modDate < lastDate.getTime()) {
        pathsFilteredByDate.add(path);
      }
    }
    return pathsFilteredByDate;
  }
}
