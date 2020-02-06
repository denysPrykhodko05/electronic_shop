package com.epam.prykhodko.task9.com.epam.prykhodko.util;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.entity.products.Notebook;
import com.epam.prykhodko.entity.products.Smartphone;
import com.epam.prykhodko.repository.ProductRepository;
import com.epam.prykhodko.repository.impl.ProductRepositoryImpl;
import com.epam.prykhodko.service.ProductService;
import com.epam.prykhodko.service.impl.ProductServiceImpl;
import com.epam.prykhodko.task9.com.epam.prykhodko.command.GetByIdProductCommand;
import com.epam.prykhodko.task9.com.epam.prykhodko.command.GetCountCommand;
import java.io.BufferedWriter;
import java.math.BigDecimal;
import java.util.Map;

public class EntityInit {

  private EntityInit() {
  }

  public static void entityInit(Map<String, Command> map, BufferedWriter bufferedWriter, StringBuilder request) {
    ProductService productService;
    ProductRepository productRepository = new ProductRepositoryImpl();

    Smartphone smartphone = new Smartphone();
    Notebook notebook = new Notebook();

    smartphone.setId(1);
    smartphone.setManufacturer("Apple");
    smartphone.setPrice(new BigDecimal(999));
    smartphone.setCommunicationStandard("4G");
    smartphone.setModelOfTouchScreen("Apple");

    notebook.setId(2);
    notebook.setManufacturer("Asus");
    notebook.setPrice(new BigDecimal(1000));
    notebook.setModelOfTouchpad("Asus");

    productService = new ProductServiceImpl(productRepository);
    productService.add(smartphone);
    productService.add(notebook);
    map.put("count", new GetCountCommand(productService, bufferedWriter));
    map.put("item", new GetByIdProductCommand(bufferedWriter, productService, request));
  }

}
