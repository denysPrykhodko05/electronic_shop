package com.epam.prykhodko.util;

import java.io.File;

public interface FileCompress {
  void write(File input, File output);

  void read(File input, File output);
}
