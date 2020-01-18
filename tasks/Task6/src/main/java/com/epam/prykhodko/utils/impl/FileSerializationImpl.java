package com.epam.prykhodko.utils.impl;


import com.epam.prykhodko.repository.ProductRepository;
import com.epam.prykhodko.repository.impl.ProductRepositoryImpl;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.utils.FileDeserialization;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class FileSerializationImpl implements FileDeserialization {

  public static final String CANNOT_WRITE_TO_FILE = "Cannot write to file!!!";
  public static final String CANNOT_READ_FILE = "Cannot read file!!!";
  public static final String CANNOT_COMPRESS_FILE = "Cannot compress file!!!";
  public static final String CANNOT_DECOMPRESS_FILE = "Cannot decompress file!!!";
  private static final Logger log = Logger.getLogger(FileSerializationImpl.class);
  private File file;

  public FileSerializationImpl(String fileName) {
    DOMConfigurator.configure("C:\\task1\\git pracrice I\\pre_prod_java_q4q1_2019\\tasks\\resources\\log4j.xml");
    this.file = new File(fileName);
  }

  @Override
  public void write(ProductRepository product) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
      oos.writeObject(product.get());
    } catch (IOException e) {
      log.error(CANNOT_WRITE_TO_FILE);
    }
  }

  @Override
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
  public ProductRepository read() {
    ProductRepository productRepository = new ProductRepositoryImpl();
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
      productRepository.add((ArrayList<Product>) ois.readObject());
      return productRepository;
    } catch (IOException | ClassNotFoundException e) {
      log.error(CANNOT_READ_FILE);
      productRepository.productInit();
    }
    return productRepository;
  }


  public String fileSize() {
    return file.length() / 1024 + " kb";
  }
}
