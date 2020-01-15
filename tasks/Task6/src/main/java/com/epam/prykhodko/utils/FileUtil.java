package com.epam.prykhodko.utils;

import com.epam.prykhodko.repository.BasketRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileUtil {

  private File file;

  public FileUtil(String fileName) {
    this.file = new File(fileName);
  }

  public void write(BasketRepository product) {
    ObjectOutputStream oos = null;
    try {
      oos = new ObjectOutputStream(new FileOutputStream(file));
      oos.writeObject(product);
    } catch (IOException e) {
      System.out.println("Cannot write to file!!!");
    }
  }

  public void write(BasketRepository product, int amount) {
    ObjectOutputStream oos = null;
    try {
      for (int i = 0; i < amount; i++) {
        oos = new ObjectOutputStream(new FileOutputStream(file,true));
        oos.writeObject(product);
      }
    } catch (IOException e) {
      System.out.println("Cannot write to file!!!");
    }
  }

  public BasketRepository read() {
    if (!file.exists()){
      System.out.println("File not found!!!");
      return null;
    }
    ObjectInputStream ois = null;
    try {
      ois = new ObjectInputStream(new FileInputStream(file));
      return (BasketRepository) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      System.out.println("Cannot read file!!!");
    }
    return null;
  }

  public String fileSize(){
    return file.length()/1024+" kb";
  }

}
