package com.epam.prykhodko.filter;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchBySizeFilter extends Handler {

  private int minSize;
  private int maxSize;

  public SearchBySizeFilter(int minSize, int maxSize, String directory) {
    super.directory = new File(directory);
    this.minSize = minSize;
    this.maxSize = maxSize;
  }

  @Override
  public boolean check() {
    List<String> paths;
    paths = findBySize(directory, minSize, maxSize);
    if (paths.isEmpty()) {
      return false;
    }
    return checkNext(paths, directory);
  }

  @Override
  public boolean check(List<String> paths, File directory) {
    List<String> tempPaths;
    tempPaths = findBySize(paths, minSize, maxSize);
    if (tempPaths.isEmpty()) {
      return false;
    }
    return checkNext(tempPaths, directory);
  }


  private List<String> findBySize(File directory, int minSize, int maxSize) {
    List<String> pathsFilteredBySize = new ArrayList<>();
    File[] list = directory.listFiles();
    long length;

    if (list == null) {
      return Collections.emptyList();
    }

    for (File file : list) {
      if (file.isDirectory()) {
        pathsFilteredBySize.addAll(findBySize(file, minSize, maxSize));
      } else {
        length = file.length();
        if (length / 1024 > minSize && length / 1024 < maxSize) {
          pathsFilteredBySize.add(file.getAbsolutePath());
        }
      }
    }
    return pathsFilteredBySize;
  }

  private List<String> findBySize(List<String> paths, int minSize, int maxSize) {
    List<String> pathsFilteredBySize = new ArrayList<>();
    for (String path : paths) {
      long length = new File(path).length();
      if (length / 1024 > minSize && length / 1024 < maxSize) {
        pathsFilteredBySize.add(path);
      }
    }
    return pathsFilteredBySize;
  }
}
