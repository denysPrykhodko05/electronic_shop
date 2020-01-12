package com.epam.prykhodko.filter;

import static com.epam.prykhodko.util.FileUtils.findFileByExtension;

import java.io.File;
import java.util.List;

public class SearchByFilenameExtension extends Handler {

  private String extension;
  private File directory;
  private List<String> paths;

  public SearchByFilenameExtension(String extension, String directory) {
    this.extension = extension;
    this.directory = new File(directory);
  }

  @Override
  public boolean check() {
    paths = findFileByExtension(extension, directory);
    if (paths==null) {
      return false;
    }
    return checkNext(paths,directory);
  }

  @Override
  public boolean check(List<String> paths, File directory) {
    this.paths = findFileByExtension(paths, extension);
    if (this.paths==null) {
      return false;
    }
    return checkNext(this.paths, directory);
  }
}
