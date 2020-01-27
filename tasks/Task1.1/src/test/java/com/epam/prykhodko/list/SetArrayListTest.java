package com.epam.prykhodko.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.entity.products.Smartphone;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SetArrayListTest {

  SetArrayList<Product> arrayList;
  Smartphone smartphone;
  Smartphone smartphone1;
  Smartphone smartphone2;

  @BeforeEach
  private void init() {
    arrayList = new SetArrayList<>();
    smartphone = new Smartphone();
    smartphone1 = new Smartphone();
    smartphone2 = new Smartphone();

    smartphone.setId(1);
    smartphone.setManufacturer("Apple");
    smartphone.setPrice(new BigDecimal(999));
    smartphone.setModelOfTouchScreen("apple");
    smartphone.setCommunicationStandard("4G");

    smartphone1.setId(2);
    smartphone1.setManufacturer("Samsung");
    smartphone1.setPrice(new BigDecimal(1000));
    smartphone1.setModelOfTouchScreen("samsung");
    smartphone1.setCommunicationStandard("5G");

    smartphone2.setId(3);
    smartphone2.setManufacturer("Nokia");
    smartphone2.setPrice(new BigDecimal(500));
    smartphone2.setCommunicationStandard("3G");
    smartphone2.setModelOfTouchScreen("nokia");
  }

  @Test
  void constructorShouldThrowIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class,
        () -> arrayList = new SetArrayList<>(Arrays.asList(smartphone, smartphone1, smartphone)));
  }

  @Test
  void constructorShouldInitialize() {
    arrayList = new SetArrayList<>(Arrays.asList(smartphone, smartphone1));
  }

  @Test
  void addShouldThrowIllegalStateException() {
    arrayList.add(smartphone);

    assertThrows(IllegalArgumentException.class, () -> arrayList.add(smartphone));
  }

  @Test
  void addShouldAddObject() {
    arrayList.add(smartphone);
    arrayList.add(smartphone1);

    int expected = 2;
    int actual = arrayList.size();

    assertEquals(expected, actual);
  }

  @Test
  void addByIndexShouldThrowIllegalArgumentException() {
    arrayList.add(smartphone);
    arrayList.add(smartphone1);

    assertThrows(IllegalArgumentException.class, () ->
        arrayList.add(0, smartphone1));
  }

  @Test
  void addByIndexShouldAddObject() {

    arrayList.add(smartphone);
    arrayList.add(smartphone1);
    arrayList.add(0, smartphone2);

    int expected = 3;
    int actual = arrayList.size();

    assertEquals(expected, actual);
  }

  @Test
  void addAllByIndexShouldThrowIllegalStateException() {
    List<Product> list = new ArrayList<>();
    arrayList.add(smartphone);
    arrayList.add(smartphone1);

    list.add(smartphone2);
    list.add(smartphone1);

    assertThrows(IllegalArgumentException.class, () ->
        arrayList.addAll(0, list));
  }

  @Test
  void addAllByIndexShouldAddCollection() {
    List<Product> list = new ArrayList<>();
    list.add(smartphone2);

    arrayList.add(smartphone);
    arrayList.add(smartphone1);

    arrayList.addAll(0, list);

    int expected = 3;
    int actual = arrayList.size();

    assertEquals(expected, actual);
  }

  @Test
  void addAllShouldAddCollection() {
    List<Product> list = new ArrayList<>();
    list.add(smartphone2);

    arrayList.add(smartphone);
    arrayList.add(smartphone1);

    arrayList.addAll(list);

    int expected = 3;
    int actual = arrayList.size();

    assertEquals(expected, actual);
  }


  @Test
  void addAllShouldThrowIllegalArgumentException() {
    List<Product> list = new ArrayList<>();
    arrayList.add(smartphone);
    arrayList.add(smartphone1);

    list.add(smartphone2);
    list.add(smartphone1);

    assertThrows(IllegalArgumentException.class, () ->
        arrayList.addAll(0, list));
  }

  @Test
  void setShouldReturnElement() {
    arrayList.add(smartphone);
    arrayList.add(smartphone1);

    assertNotNull(arrayList.set(0, smartphone));
  }

  @Test
  void setShouldThrowIllegalArgumentException() {
    arrayList.add(smartphone);
    arrayList.add(smartphone1);

    assertThrows(IllegalArgumentException.class, () -> arrayList.set(1, smartphone));
  }

  @Test
  void setShouldReturnNotNull() {
    arrayList.add(smartphone);
    arrayList.add(smartphone1);

    assertNotNull(arrayList.set(0, smartphone2));
  }

}