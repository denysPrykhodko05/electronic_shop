package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.repository.ProductRepositoryImpl;
import com.epam.prykhodko.service.ProductService;

public class GetAllProductsCommand implements Command {

  private final ProductService productService;

  public GetAllProductsCommand(ProductRepositoryImpl productRepositoryImpl) {
    productService = new ProductService(productRepositoryImpl);
  }

  @Override
  public void execute() {
    productService.getAll().forEach(System.out::println);
  }
}
