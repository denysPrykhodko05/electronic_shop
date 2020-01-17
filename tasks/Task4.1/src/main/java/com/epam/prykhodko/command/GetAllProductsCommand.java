package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.service.ProductService;
import com.epam.prykhodko.service.impl.ProductServiceImpl;

public class GetAllProductsCommand implements Command {

  private final ProductService productService;

  public GetAllProductsCommand(com.epam.prykhodko.repository.ProductRepository productRepository) {
    productService = new ProductServiceImpl(productRepository);
  }

  @Override
  public void execute() {
    productService.getAll().forEach(System.out::println);
  }
}
