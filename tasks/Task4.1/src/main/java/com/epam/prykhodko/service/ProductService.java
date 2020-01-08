package com.epam.prykhodko.service;

import com.epam.prykhodko.repository.repositoryInterface.ProductRepository;
import com.epam.prykhodko.task1.entity.Product;
import java.util.List;

public class ProductService {

  private ProductRepository products;

  public ProductService(ProductRepository products) {
    this.products = products;
  }

  public List<Product> getAll() {
    return products.get();
  }

  public Product getById(int id) {
    for (Product product : products.get()) {
      if (product.getId()==id){
        return product;
      }
    }
    return null;
  }

}
