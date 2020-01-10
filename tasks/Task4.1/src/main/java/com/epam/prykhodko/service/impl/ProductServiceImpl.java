package com.epam.prykhodko.service.impl;

import com.epam.prykhodko.repository.ProductRepository;
import com.epam.prykhodko.service.ProductService;
import com.epam.prykhodko.task1.entity.Product;
import java.util.List;

public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<Product> getAll() {
    return productRepository.get();
  }

  @Override
  public Product getById(int id) {
    return productRepository.getById(id).get();
  }

}
