package com.epam.prykhodko.filter;

import static com.epam.prykhodko.util.FileUtils.findByDate;

import java.io.File;
import java.util.Date;
import java.util.List;

public class SearchByDate extends Handler {

  private File directory;
  private Date firstDate;
  private Date lastDate;
  private List<String> paths;

  public SearchByDate(Date firstDate, Date lastDate, String directory) {
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
}
