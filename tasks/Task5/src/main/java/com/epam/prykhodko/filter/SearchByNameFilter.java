package com.epam.prykhodko.filter;

import static com.epam.prykhodko.util.FileUtils.findFileByName;

import java.io.File;
import java.util.List;

public class SearchByNameFilter extends Handler {

  private final String name;
  private final File directory;

  public SearchByNameFilter(String name, String directory) {
    this.name = name;
    this.directory = new File(directory);
  }


  @Override
  public boolean check() {
    List<String> paths = findFileByName(name, directory);
    if (paths == null) {
      return false;
    }
    return checkNext(paths, directory);
  }

  @Override
  public boolean check(List<String> paths, File directory) {
    throw new UnsupportedOperationException();
  }

}
