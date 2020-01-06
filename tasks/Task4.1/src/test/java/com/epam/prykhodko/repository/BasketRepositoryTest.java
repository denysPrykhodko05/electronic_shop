package com.epam.prykhodko.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.task1.entity.Smartphone;
import org.junit.jupiter.api.Test;

class BasketRepositoryTest {

  @Test
  void addSizeSholudBeEqualOne() {
    Product product = new Smartphone();
    BasketRepository basketRepository = new BasketRepository();
    basketRepository.add(product,2);

    int expected = 1;
    int actual = basketRepository.get().size();

    assertEquals(expected,actual);
  }

  @Test
  void getShouldBeNull() {
    Product product = new Smartphone();
    BasketRepository basketRepository = new BasketRepository();
    basketRepository.add(product,2);
    assertNotNull(basketRepository.get());
  }

  @Test
  void getCache() {
    Product product = new Smartphone();
    BasketRepository basketRepository = new BasketRepository();
    basketRepository.add(product,2);
    assertNotNull(basketRepository.getCache());
  }
}