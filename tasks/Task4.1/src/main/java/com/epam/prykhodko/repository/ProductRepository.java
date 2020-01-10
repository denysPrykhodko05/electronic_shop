package com.epam.prykhodko.repository;

import com.epam.prykhodko.task1.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

  public List<Product> get();
  Optional<Product> getById(int id);
}
