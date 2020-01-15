package com.epam.prykhodko.filter;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchBySizeFilter extends Handler {

  private final int minSize;
  private final int maxSize;

  public SearchBySizeFilter(int minSize, int maxSize, String directory) {
    super(new File(directory));
    this.minSize = minSize;
    this.maxSize = maxSize;
  }

  @Override
  protected List<String> findFiles(File directory) {
    return findBySize(directory, minSize, maxSize);
  }

  @Override
  protected List<String> findFiles(List<String> paths) {
    return findBySize(paths, minSize, maxSize);
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
