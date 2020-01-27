package com.epam.prykhodko.list.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.entity.products.Smartphone;
import com.epam.prykhodko.repository.impl.BasketRepositoryImpl;
import org.junit.jupiter.api.Test;

class BasketRepositoryTest {

  @Test
  void addSizeSholudBeEqualOne() {
    Product product = new Smartphone();
    BasketRepositoryImpl basketRepository = new BasketRepositoryImpl();
    basketRepository.add(product,2);

    int expected = 1;
    int actual = basketRepository.get().size();

    assertEquals(expected,actual);
  }

  @Test
  void getShouldBeNull() {
    Product product = new Smartphone();
    BasketRepositoryImpl basketRepository = new BasketRepositoryImpl();
    basketRepository.add(product,2);
    assertNotNull(basketRepository.get());
  }
}