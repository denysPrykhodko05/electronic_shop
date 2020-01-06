package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.repository.ProductRepository;
import com.epam.prykhodko.service.ProductService;

public class GetAllProductsCommand implements Command {

  private ProductService productService;

  public GetAllProductsCommand(ProductRepository productRepository) {
    productService = new ProductService(productRepository);
  }

  @Override
  public void execute() {
    productService.getAll().forEach(System.out::println);
  }
}
