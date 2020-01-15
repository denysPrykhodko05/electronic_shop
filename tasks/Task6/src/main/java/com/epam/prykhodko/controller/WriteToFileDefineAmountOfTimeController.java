package com.epam.prykhodko.controller;

import com.epam.prykhodko.repository.BasketRepository;
import com.epam.prykhodko.repository.impl.BasketRepositoryImpl;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.task1.entity.Smartphone;
import com.epam.prykhodko.utils.FileUtil;
import java.math.BigDecimal;

public class WriteToFileDefineAmountOfTimeController {

  public static void main(String[] args) {
    int amount;
    //amount = ConsoleScanner.readInt();
    FileUtil fileUtil = new FileUtil("2.xml");
    Product product = new Smartphone(1,new BigDecimal(1),"Apple","4G","Apple");
    BasketRepository basket = new BasketRepositoryImpl();

    basket.add(product,5);
    fileUtil.write(basket,5);
    System.out.println(fileUtil.fileSize());
  }
}
