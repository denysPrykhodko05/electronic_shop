package com.epam.prykhodko.repository.repositoryInterface;

import com.epam.prykhodko.task1.entity.Product;
import java.util.List;

public interface ProductRepository {

  public List<Product> get();
}
