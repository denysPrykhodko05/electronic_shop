package com.epam.prykhodko.util;

import com.epam.prykhodko.repository.ProductRepository;

public interface FileDeserialization {

  void write(ProductRepository product);

  void write(ProductRepository product, int amount);

  ProductRepository read();
}
