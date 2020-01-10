package com.epam.prykhodko.filter;

import java.io.File;
import java.util.List;

public abstract class Handler {

  private Handler next;


  public Handler linkWith(Handler next) {
    this.next = next;
    return next;
  }

  public abstract boolean check(String parameter, File directory);

  public abstract boolean check(List<String> paths, File directory);

  protected boolean checkNext(String name, File file) {
    if (next == null) {
      return true;
    }
    return next.check(name, file);
  }

  protected boolean checkNext(List<String> paths, File file) {
    if (next == null) {
      return true;
    }
    return next.check(paths, file);
  }
}
