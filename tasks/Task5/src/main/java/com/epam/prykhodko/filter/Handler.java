package com.epam.prykhodko.filter;

import java.io.File;
import java.util.List;

public abstract class Handler {

  private File directory;
  private Handler next;

  public Handler(File directory) {
    this.directory = directory;
  }

  public Handler linkWith(Handler next) {
    this.next = next;
    return next;
  }

  public boolean check(){
    List<String> paths = findFiles(directory);
    return !paths.isEmpty() && checkNext(paths,directory);
  }

  public boolean check(List<String> paths, File directory){
    List<String> tempPaths = findFiles(paths);
    return !tempPaths.isEmpty() && checkNext(paths,directory);
  }


  protected boolean checkNext(List<String> paths, File file) {
    if (next == null) {
      System.out.println("Found files: ");
      paths.forEach(System.out::println);
      return true;
    }
    return next.check(paths, file);
  }

  protected abstract List<String> findFiles(File directory);

  protected abstract List<String> findFiles(List<String> paths);

}
