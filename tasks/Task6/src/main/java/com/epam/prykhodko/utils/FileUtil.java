package com.epam.prykhodko.utils;


import com.epam.prykhodko.repository.ProductRepository;
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

  public static final String CANNOT_WRITE_TO_FILE = "Cannot write to file!!!";
  public static final String FILE_NOT_FOUND = "File not found!!!";
  public static final String CANNOT_READ_FILE = "Cannot read file!!!";
  public static final String CANNOT_COMPRESS_FILE = "Cannot compress file!!!";
  public static final String CANNOT_DECOMPRESS_FILE = "Cannot decompress file!!!";

  private static final Logger log = Logger.getLogger(FileUtil.class);
  private File file;

  public FileUtil(String fileName) {
    this.file = new File(fileName);
  }

  public void serialize(ProductRepository product) {
    ObjectOutputStream oos = null;
    try {
      oos = new ObjectOutputStream(new FileOutputStream(file));
      oos.writeObject(product);
    } catch (IOException e) {
       log.error(CANNOT_WRITE_TO_FILE);
    }
  }

  public void serialize(ProductRepository product, int amount) {
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

  public ProductRepository deserialize() {
    ObjectInputStream ois = null;
    try {
      if (!file.exists()) {
        file.createNewFile();
        log.error(FILE_NOT_FOUND);
        return null;
      }
      ois = new ObjectInputStream(new FileInputStream(file));
      return (ProductRepository) ois.readObject();
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

  public void gzipDecompress(File input, File output) {
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
