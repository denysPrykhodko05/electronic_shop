package com.epam.prykhodko.repository;

import com.epam.prykhodko.task1.entity.Notebook;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.task1.entity.Smartphone;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Products {

  private List<Product> productList;
  private Smartphone smartphone;
  private Smartphone smartphone1;
  private Notebook notebook;
  private Notebook notebook1;

  public Products() {
    smartphone = new Smartphone();
    smartphone1 = new Smartphone();
    notebook = new Notebook();
    notebook1 = new Notebook();
    productList = new ArrayList<>(Arrays.asList(smartphone, smartphone1, notebook, notebook1));

    productInit();
  }

  public List<Product> getAll() {
    return productList;
  }

  private void productInit() {
    smartphone.setId(1);
    smartphone.setManufacturer("Apple");
    smartphone.setPrice(new BigDecimal(999));
    smartphone.setCommunicationStandard("4G");
    smartphone.setModelOfTouchScreen("Apple");

    smartphone1.setId(2);
    smartphone1.setManufacturer("Samsung");
    smartphone1.setPrice(new BigDecimal(1500));
    smartphone1.setCommunicationStandard("5G");
    smartphone1.setModelOfTouchScreen("Samsung");

    notebook1.setId(3);
    notebook1.setManufacturer("Asus");
    notebook1.setPrice(new BigDecimal(1000));
    notebook1.setModelOfTouchpad("Asus");

    notebook.setId(4);
    notebook.setManufacturer("Acer");
    notebook.setPrice(new BigDecimal(2000));
    notebook.setModelOfTouchpad("Acer");
  }

}
