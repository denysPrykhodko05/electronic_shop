package com.epam.prykhodko.utils.impl;


import com.epam.prykhodko.repository.ProductRepository;
import com.epam.prykhodko.repository.impl.ProductRepositoryImpl;
import com.epam.prykhodko.utils.FileReaderWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.log4j.Logger;

public class FileSerializationImpl implements FileReaderWriter {

  public static final String CANNOT_WRITE_TO_FILE = "Cannot write to file!!!";
  public static final String CANNOT_READ_FILE = "Cannot read file!!!";
  public static final String CANNOT_COMPRESS_FILE = "Cannot compress file!!!";
  public static final String CANNOT_DECOMPRESS_FILE = "Cannot decompress file!!!";
  private static final Logger log = Logger.getLogger(FileSerializationImpl.class);
  private File file;

  public FileSerializationImpl(String fileName) {
    this.file = new File(fileName);
  }

  @Override
  public void write(ProductRepository productRepository) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
      oos.writeObject(productRepository);
    } catch (IOException e) {
      log.error(CANNOT_WRITE_TO_FILE);
    }
  }

  public void write(ProductRepository product, int amount) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true))) {
      for (int i = 0; i < amount; i++) {
        oos.writeObject(product);
      }
    } catch (IOException e) {
      log.error(CANNOT_WRITE_TO_FILE);
    }
  }

  @Override
  public void write(File input, File output) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void read(File input, File output) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ProductRepository read() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
      return (com.epam.prykhodko.repository.ProductRepository) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      log.error(CANNOT_READ_FILE);
      return new ProductRepositoryImpl();
    }

  }


  public String fileSize() {
    return file.length() / 1024 + " kb";
  }
}
