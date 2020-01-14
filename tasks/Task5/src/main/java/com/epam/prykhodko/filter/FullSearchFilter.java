package com.epam.prykhodko.filter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FullSearchFilter extends Handler {

  public FullSearchFilter(String directory) {
    super.directory = new File(directory);
  }

  @Override
  public boolean check() {
    List<String> paths = findAll(directory);
    return checkNext(paths, directory);
  }

  @Override
  public boolean check(List<String> paths, File directory) {
    throw new UnsupportedOperationException();
  }

  private List<String> findAll(File directory) {
    List<String> pathsFilteredByName = new ArrayList<>();
    File[] list = directory.listFiles();

    if (list == null) {
      return Collections.emptyList();
    }
    for (File file : list) {
      if (file.isDirectory()) {
        pathsFilteredByName.addAll(findAll(file));
      } else {
        pathsFilteredByName.add(file.getAbsolutePath());
      }
    }
    return pathsFilteredByName;
  }
}