package com.epam.prykhodko.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ProductRepositoryTest {

  @Test
  void get() {
    int expected = 6;
    int actual = new ProductRepository().get().size();
    assertEquals(expected,actual);
  }
}