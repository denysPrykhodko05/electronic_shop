package com.epam.prykhodko.filter;

import static com.epam.prykhodko.constant.Constants.REG_DOT;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchByNameFilter extends Handler {

  private final String name;

  public SearchByNameFilter(String name, String directory) {
    this.name = name;
    super.directory = new File(directory);
  }


  @Override
  public boolean check() {
    List<String> paths = findFileByName(name, directory);
    if (paths.isEmpty()) {
      return false;
    }
    return checkNext(paths, directory);
  }

  @Override
  public boolean check(List<String> paths, File directory) {
    throw new UnsupportedOperationException();
  }

  private List<String> findFileByName(String name, File directory) {
    List<String> pathsFilteredByName = new ArrayList<>();
    File[] list = directory.listFiles();
    if (list == null) {
      return Collections.emptyList();
    }

    for (File file : list) {
      if (file.isDirectory()) {
        pathsFilteredByName.addAll(findFileByName(name, file));
      } else {
        String foundName = file.getName().split(REG_DOT)[INTEGER_ZERO];
        if (name.equalsIgnoreCase(foundName)) {
          pathsFilteredByName.add(file.getAbsolutePath());
        }
      }
    }
    return pathsFilteredByName;
  }
}
