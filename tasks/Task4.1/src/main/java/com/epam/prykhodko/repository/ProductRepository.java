package com.epam.prykhodko.repository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

  List<com.epam.prykhodko.task1.entity.ProductRepository> get();
  Optional<com.epam.prykhodko.task1.entity.ProductRepository> getById(int id);
  void add(com.epam.prykhodko.task1.entity.ProductRepository product);
}
