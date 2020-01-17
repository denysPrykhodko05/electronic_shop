package com.epam.prykhodko.service.impl;

import com.epam.prykhodko.service.ProductService;
import com.epam.prykhodko.task1.entity.ProductRepository;
import java.util.List;

public class ProductServiceImpl implements ProductService {

  private final com.epam.prykhodko.repository.ProductRepository productRepository;

  public ProductServiceImpl(com.epam.prykhodko.repository.ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<ProductRepository> getAll() {
    return productRepository.get();
  }

  @Override
  public ProductRepository getById(int id) {
    return productRepository.getById(id).get();
  }

  @Override
  public void add(ProductRepository product){
    productRepository.add(product);
  }
}
