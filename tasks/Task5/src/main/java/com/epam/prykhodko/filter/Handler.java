package com.epam.prykhodko.filter;

import java.io.File;
import java.util.List;

public abstract class Handler {

  private Handler next;
  private Handler last;
  private boolean flag;

  public Handler linkWith(Handler next) {
    if (!flag) {
      this.next = next;
      last = next;
      flag = true;
      return next;
    }
    last.next = next;
    last = next;
    return next;
  }

  public abstract boolean check();

  public abstract boolean check(List<String> paths, File directory);


  protected boolean checkNext(List<String> paths, File file) {
    if (next == null) {
      System.out.println("Found files: ");
      paths.forEach(System.out::println);
      return true;
    }
    return next.check(paths, file);
  }
}
