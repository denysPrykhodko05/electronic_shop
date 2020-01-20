package com.epam.prykhodko.utils.impl;

import static com.epam.prykhodko.utils.impl.FileSerializationImpl.CANNOT_COMPRESS_FILE;
import static com.epam.prykhodko.utils.impl.FileSerializationImpl.CANNOT_DECOMPRESS_FILE;

import com.epam.prykhodko.utils.FileCompress;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.log4j.Logger;

public class FileCompressorImpl implements FileCompress {

  private static final Logger LOGGER = Logger.getLogger(FileCompressorImpl.class);

  @Override
  public void write(File input, File output) {
    try (GZIPOutputStream outputStream = new GZIPOutputStream(new FileOutputStream(output));
        FileInputStream inputStream = new FileInputStream(input)) {
      byte[] buffer = new byte[1024];
      int len;
      while ((len = inputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, len);
      }
    } catch (IOException e) {
      LOGGER.error(CANNOT_COMPRESS_FILE);
    }
  }

  @Override
  public void read(File input, File output) {
    try (GZIPInputStream in = new GZIPInputStream(new FileInputStream(input));
        FileOutputStream out = new FileOutputStream(output)) {
      byte[] buffer = new byte[1024];
      int len;
      while ((len = in.read(buffer)) != -1) {
        out.write(buffer, 0, len);
      }
    } catch (IOException e) {
      LOGGER.error(CANNOT_DECOMPRESS_FILE);
    }
  }
}
