package com.epam.prykhodko.filter;

import static com.epam.prykhodko.constant.Constants.REG_DOT;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_TWO;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchByFilenameExtensionFilter extends Handler {

  private final String extension;

  public SearchByFilenameExtensionFilter(String extension, String directory) {
    super(new File(directory));
    this.extension = extension;
  }

  @Override
  protected List<String> findFiles(File directory) {
    return findFileByExtension(extension, directory);
  }

  @Override
  protected List<String> findFiles(List<String> paths) {
    return findFileByExtension(paths, extension);
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
