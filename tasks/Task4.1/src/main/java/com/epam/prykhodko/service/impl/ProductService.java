package com.epam.prykhodko.service.impl;

import com.epam.prykhodko.repository.ProductRepository;
import com.epam.prykhodko.task1.entity.Product;
import java.util.List;

public class ProductService implements com.epam.prykhodko.service.ProductService {

  private ProductRepository products;

  public ProductService(ProductRepository products) {
    this.products = products;
  }

  public List<Product> getAll() {
    return products.get();
  }

  public Product getById(int id) {
    return products.get().stream().filter(e -> e.getId() == id).findFirst().get();
  }

}
