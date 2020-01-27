package com.epam.prykhodko.command.impl;

import static com.epam.prykhodko.constant.Constants.STRING_ONE;
import static com.epam.prykhodko.constant.Constants.STRING_ZERO;
import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.prykhodko.entity.invoke.input.impl.RandomInput;
import com.epam.prykhodko.repository.ProductRepository;
import com.epam.prykhodko.repository.impl.ProductRepositoryImpl;
import com.epam.prykhodko.service.ProductService;
import com.epam.prykhodko.service.impl.ProductServiceImpl;
import java.io.ByteArrayInputStream;
import java.util.Locale;
import org.junit.jupiter.api.Test;

class AddToProductListByReflectCommandTest {

  @Test
  void executeShouldAddProduct() {
    ByteArrayInputStream in = new ByteArrayInputStream(STRING_ONE.getBytes());
    System.setIn(in);
    ProductRepository productRepository = new ProductRepositoryImpl();
    ProductService productService = new ProductServiceImpl(productRepository);
    AddToProductListByReflectCommand add = new AddToProductListByReflectCommand(new RandomInput(),
        new Locale("ru"), productService);
    add.execute();

    int expected = 1;
    int actual = productService.getAll().size();

    assertEquals(expected,actual);
  }

  @Test
  void executeShouldNotAddProduct() {
    ByteArrayInputStream in = new ByteArrayInputStream(STRING_ZERO.getBytes());
    System.setIn(in);
    ProductRepository productRepository = new ProductRepositoryImpl();
    ProductService productService = new ProductServiceImpl(productRepository);
    AddToProductListByReflectCommand add = new AddToProductListByReflectCommand(new RandomInput(),
        new Locale("ru"), productService);
    add.execute();

    int expected = 0;
    int actual = productService.getAll().size();

    assertEquals(expected,actual);
  }

  @Test
  void executeShouldNotFirstlyCreateProductAndAfterShould() {
    String input = "3" +lineSeparator()+ 1;
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ProductRepository productRepository = new ProductRepositoryImpl();
    ProductService productService = new ProductServiceImpl(productRepository);
    AddToProductListByReflectCommand add = new AddToProductListByReflectCommand(new RandomInput(),
        new Locale("ru"), productService);
    add.execute();

    int expected = 1;
    int actual = productService.getAll().size();

    assertEquals(expected,actual);
  }
}