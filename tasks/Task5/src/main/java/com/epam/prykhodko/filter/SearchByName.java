package com.epam.prykhodko.filter;

import static com.epam.prykhodko.util.Utils.findFileByName;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SearchByName extends Handler {

  private boolean flag = false;
  private List<String> paths = new ArrayList<>();

  @Override
  public boolean check(String name, File directory) {
    flag = findFileByName(name, directory);
    if (!flag) {
      return false;
    }
    System.out.println();
    return checkNext(paths, directory);
  }

  @Override
  public boolean check(List<String> paths, File directory) {
    throw new UnsupportedOperationException();
  }

}
