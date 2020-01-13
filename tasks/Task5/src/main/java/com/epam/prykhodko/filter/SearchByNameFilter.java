package com.epam.prykhodko.filter;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SearchByNameFilter extends Handler {

  private final String name;
  private final File directory;
  private final List<String> pathsFilteredByName = new ArrayList<>();

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

  public List<String> findFileByName(String name, File directory) {
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
          pathsFilteredByName.add(file.getAbsolutePath());
        }
      }
    }
    return pathsFilteredByName;
  }
}
