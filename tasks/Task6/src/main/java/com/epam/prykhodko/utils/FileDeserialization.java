package com.epam.prykhodko.utils;

import com.epam.prykhodko.repository.ProductRepository;

public interface FileDeserialization {

  void write(ProductRepository product);

  ProductRepository read();

  void write(ProductRepository product, int amount);
}
