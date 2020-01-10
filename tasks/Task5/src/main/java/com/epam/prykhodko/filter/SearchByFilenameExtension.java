package com.epam.prykhodko.filter;

import static com.epam.prykhodko.util.Utils.findFileByExtension;

import java.io.File;
import java.util.List;

public class SearchByFilenameExtension extends Handler {

  boolean flag = false;


  public SearchByFilenameExtension() {

  }

  @Override
  public boolean check(String name, File directory) {
     flag = findFileByExtension(name, directory);
     if (!flag){
       return false;
     }
    return false;
  }

  @Override
  public boolean check(List<String> paths, File directory) {
    return false;
  }
}
