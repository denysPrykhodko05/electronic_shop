package com.epam.prykhodko.service.impl;

import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.repository.ProductRepository;
import com.epam.prykhodko.service.ProductService;
import java.util.List;

public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public ProductServiceImpl(com.epam.prykhodko.repository.ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<Product> getAll() {
    return productRepository.get();
  }

  @Override
  public Product getById(int id) {
    if (productRepository.getById(id).isPresent()) {
      return productRepository.getById(id).get();
    }
    return null;
  }

  @Override
  public void add(Product product) {
    productRepository.add(product);
  }
}
