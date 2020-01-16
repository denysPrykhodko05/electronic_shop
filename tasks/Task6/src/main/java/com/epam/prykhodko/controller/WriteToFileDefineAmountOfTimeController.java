package com.epam.prykhodko.controller;

import com.epam.prykhodko.repository.BasketRepository;
import com.epam.prykhodko.repository.impl.BasketRepositoryImpl;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.task1.entity.Smartphone;
import com.epam.prykhodko.util.ConsoleScanner;
import com.epam.prykhodko.utils.FileUtil;
import java.io.File;
import java.math.BigDecimal;

public class WriteToFileDefineAmountOfTimeController {

  public static void main(String[] args) {
    int amount;
    amount = ConsoleScanner.readInt();
    FileUtil fileUtil = new FileUtil("2.xml");
    Product product = new Smartphone(1,new BigDecimal(1),"Apple","4G","Apple");
    BasketRepository basket = new BasketRepositoryImpl();

    basket.add(product,5);
    fileUtil.serialize(basket,amount);
    System.out.println(fileUtil.fileSize());
    fileUtil.gzipCompress(new File("2.xml"),new File("2.1Compressed.gz"));
    fileUtil.gzipDecompress(new File("2.1Compressed.gz"),new File("2.1Decompressed.xml"));
  }
}
