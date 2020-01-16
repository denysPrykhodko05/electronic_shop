package com.epam.prykhodko.utils;

import static com.epam.prykhodko.constants.Constants.CANNOT_COMPRESS_FILE;
import static com.epam.prykhodko.constants.Constants.CANNOT_DECOMPRESS_FILE;
import static com.epam.prykhodko.constants.Constants.CANNOT_READ_FILE;
import static com.epam.prykhodko.constants.Constants.CANNOT_WRITE_TO_FILE;
import static com.epam.prykhodko.constants.Constants.FILE_NOT_FOUND;

import com.epam.prykhodko.repository.BasketRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.log4j.Logger;

public class FileUtil {

  private static final Logger log =Logger.getLogger(FileUtil.class);
  private File file;

  public FileUtil(String fileName) {
    this.file = new File(fileName);
  }

  public void serialize(BasketRepository product) {
    ObjectOutputStream oos = null;
    try {
      oos = new ObjectOutputStream(new FileOutputStream(file));
      oos.writeObject(product);
    } catch (IOException e) {
      log.error(CANNOT_WRITE_TO_FILE);
    }
  }

  public void serialize(BasketRepository product, int amount) {
    ObjectOutputStream oos = null;
    try {
      for (int i = 0; i < amount; i++) {
        oos = new ObjectOutputStream(new FileOutputStream(file, true));
        oos.writeObject(product);
      }
    } catch (IOException e) {
      log.error(CANNOT_WRITE_TO_FILE);
    }
  }

  public BasketRepository deserialize() {
    if (!file.exists()) {
      log.error(FILE_NOT_FOUND);
      return null;
    }
    ObjectInputStream ois = null;
    try {
      ois = new ObjectInputStream(new FileInputStream(file));
      return (BasketRepository) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      System.out.println(CANNOT_READ_FILE);
    }
    return null;
  }

  public void gzipCompress(File input, File output) {
    try (GZIPOutputStream outputStream = new GZIPOutputStream(new FileOutputStream(output));
        FileInputStream inputStream = new FileInputStream(input)) {
      byte[] buffer = new byte[1024];
      int len;
      while ((len = inputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, len);
      }
    } catch (IOException e) {
      log.error(CANNOT_COMPRESS_FILE);
    }
  }

  public void gzipDecompress(File input, File output){
    try (GZIPInputStream in = new GZIPInputStream(new FileInputStream(input));
        FileOutputStream out = new FileOutputStream(output)) {
      byte[] buffer = new byte[1024];
      int len;
      while ((len = in.read(buffer)) != -1) {
        out.write(buffer, 0, len);
      }
    } catch (IOException e) {
      log.error(CANNOT_DECOMPRESS_FILE);
    }
  }

  public String fileSize() {
    return file.length() / 1024 + " kb";
  }
}
