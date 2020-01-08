package com.epam.prykhodko.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ProductRepositoryImplTest {

  @Test
  void get() {
    int expected = 6;
    int actual = new ProductRepositoryImpl().get().size();
    assertEquals(expected,actual);
  }
}