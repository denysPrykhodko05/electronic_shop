package com.epam.prykhodko.controller;

import com.epam.prykhodko.repository.BasketRepository;
import com.epam.prykhodko.repository.impl.BasketRepositoryImpl;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.task1.entity.Smartphone;
import com.epam.prykhodko.utils.FileUtil;
import java.math.BigDecimal;

public class WriteToFileController {

  public static void main(String[] args) {
    FileUtil fileUtil = new FileUtil("2.xml");
    System.out.println(fileUtil.deserialize());
    Product product = new Smartphone(1,new BigDecimal(1),"Apple","4G","Apple");
    BasketRepository basket = new BasketRepositoryImpl();
    basket.add(product,1);
    fileUtil.serialize(basket);
  }
}
