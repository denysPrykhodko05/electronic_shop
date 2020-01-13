package com.epam.prykhodko.filter;

import static com.epam.prykhodko.util.FileUtils.findBySize;

import java.io.File;
import java.util.List;

public class SearchBySizeFilter extends Handler {
  private File directory;
  private int minSize;
  private int maxSize;
  private List<String> paths;

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
}
