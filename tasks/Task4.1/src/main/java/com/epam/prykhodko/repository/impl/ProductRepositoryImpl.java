package com.epam.prykhodko.repository.impl;

import com.epam.prykhodko.repository.ProductRepository;
import com.epam.prykhodko.task1.entity.Notebook;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.task1.entity.Smartphone;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository, Serializable {

  private final List<Product> productList = new ArrayList<>();

  public ProductRepositoryImpl() {
    productInit();
  }

  @Override
  public List<Product> get() {
    return productList;
  }

  @Override
  public Optional<Product> getById(int id) {
    return productList.stream().filter(e -> e.getId() == id).findFirst();
  }

  @Override
  public void add(Product product){
    productList.add(product);
  }

  private void productInit() {
    final Smartphone smartphone = new Smartphone(1, new BigDecimal(999), "Apple",
        "4G", "Apple");
    final Smartphone smartphone1 = new Smartphone(2, new BigDecimal(999), "Samsung",
        "5G", "Samsung");
    final Notebook notebook = new Notebook(3, new BigDecimal(1000), "Asus", "Asus");
    final Notebook notebook1 = new Notebook(4, new BigDecimal(2000), "Acer", "Acer");
    final Notebook notebook2 = new Notebook(5, new BigDecimal(2000), "Apple", "Apple");
    final Smartphone smartphone2 = new Smartphone(6, new BigDecimal(999), "Nokia",
        "3G", "Nokia");

    productList.add(smartphone);
    productList.add(smartphone1);
    productList.add(smartphone2);
    productList.add(notebook);
    productList.add(notebook1);
    productList.add(notebook2);
  }
}
