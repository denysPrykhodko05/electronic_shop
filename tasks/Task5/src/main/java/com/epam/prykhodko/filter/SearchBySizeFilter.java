package com.epam.prykhodko.filter;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SearchBySizeFilter extends Handler {
  private File directory;
  private int minSize;
  private int maxSize;
  private List<String> paths;
  public final List<String> pathsFilteredBySize = new ArrayList<>();

  public SearchBySizeFilter(int minSize, int maxSize, String directory){
    this.directory = new File(directory);
    this.minSize=minSize;
    this.maxSize=maxSize;
  }

  @Override
  public boolean check() {
    paths = findBySize(directory,minSize,maxSize);
    if (this.paths == null){
      return false;
    }
    return checkNext(paths, directory);
  }

  @Override
  public boolean check(List<String> paths, File directory) {
    this.paths = findBySize(paths,minSize,maxSize);
    if (this.paths == null){
      return false;
    }
    return checkNext(this.paths,directory);
  }

  public List<String> findBySize(File directory, int minSize, int maxSize) {
    File[] list = directory.listFiles();
    long length;

    if (list == null) {
      return null;
    }

    for (File file : list) {
      if (file.isDirectory()) {
        findBySize(file, minSize, maxSize);
      } else {
        length = file.length();
        if (length / 1024 > minSize && length / 1024 < maxSize) {
          pathsFilteredBySize.add(file.getAbsolutePath());
        }
      }
    }
    return pathsFilteredBySize;
  }

  public List<String> findBySize(List<String> paths, int minSize, int maxSize) {
    for (String path : paths) {
      long length = new File(path).length();
      if (length / 1024 > minSize && length / 1024 < maxSize) {
        pathsFilteredBySize.add(path);
      }
    }
    return pathsFilteredBySize;
  }
}
