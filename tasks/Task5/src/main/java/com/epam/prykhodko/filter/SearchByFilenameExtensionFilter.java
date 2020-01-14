package com.epam.prykhodko.filter;

import static com.epam.prykhodko.constant.Constants.REG_DOT;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_TWO;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchByFilenameExtensionFilter extends Handler {

  private String extension;

  public SearchByFilenameExtensionFilter(String extension, String directory) {
    this.extension = extension;
    super.directory = new File(directory);
  }

  @Override
  public boolean check() {
    List<String> paths;
    paths = findFileByExtension(extension, directory);
    if (paths == null) {
      return false;
    }
    return checkNext(paths, directory);
  }

  @Override
  public boolean check(List<String> paths, File directory) {
    List<String> tempPaths;
    tempPaths = findFileByExtension(paths, extension);
    if (tempPaths.isEmpty()) {
      return false;
    }
    return checkNext(tempPaths, directory);
  }

  private List<String> findFileByExtension(String extension, File directory) {
    List<String> pathsFilteredByExtension = new ArrayList<>();
    File[] list = directory.listFiles();
    if (list == null) {
      return Collections.emptyList();
    }

    for (File file : list) {
      if (file.isDirectory()) {
        pathsFilteredByExtension.addAll(findFileByExtension(extension, file));
      } else {
        String[] foundExtension = file.getName().split(REG_DOT);
        if (foundExtension.length != INTEGER_TWO) {
          continue;
        }
        if (extension.equalsIgnoreCase(foundExtension[INTEGER_ONE])) {
          pathsFilteredByExtension.add(file.getAbsolutePath());
        }
      }
    }
    return pathsFilteredByExtension;
  }

  private List<String> findFileByExtension(List<String> paths, String extension) {
    List<String> pathsFilteredByExtension = new ArrayList<>();
    for (String path : paths) {
      if (path.split(REG_DOT)[INTEGER_ONE].equalsIgnoreCase(extension)) {
        pathsFilteredByExtension.add(path);
      }
    }
    return pathsFilteredByExtension;
  }

}
