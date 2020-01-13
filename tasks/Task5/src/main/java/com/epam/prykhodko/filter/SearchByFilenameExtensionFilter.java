package com.epam.prykhodko.filter;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_TWO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SearchByFilenameExtensionFilter extends Handler {

  private String extension;
  private File directory;
  private List<String> paths;
  public final List<String> pathsFilteredByExtension = new ArrayList<>();

  public SearchByFilenameExtensionFilter(String extension, String directory) {
    this.extension = extension;
    this.directory = new File(directory);
  }

  @Override
  public boolean check() {
    paths = findFileByExtension(extension, directory);
    if (paths == null) {
      return false;
    }
    return checkNext(paths, directory);
  }

  @Override
  public boolean check(List<String> paths, File directory) {
    this.paths = findFileByExtension(paths, extension);
    if (this.paths == null) {
      return false;
    }
    return checkNext(this.paths, directory);
  }

  public List<String> findFileByExtension(String extension, File directory) {

    File[] list = directory.listFiles();
    if (list == null) {
      return null;
    }

    for (File file : list) {
      if (file.isDirectory()) {
        findFileByExtension(extension, file);
      } else {
        String[] foundExtension = file.getName().split("\\.");
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

  public List<String> findFileByExtension(List<String> paths, String extension) {
    for (String path : paths) {
      if (path.split("\\.")[INTEGER_ONE].equalsIgnoreCase(extension)) {
        pathsFilteredByExtension.add(path);
      }
    }
    return pathsFilteredByExtension;
  }

}
