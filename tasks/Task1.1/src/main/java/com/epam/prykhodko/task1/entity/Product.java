package com.epam.prykhodko.task1.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Product implements Serializable {

  private int id;
  private BigDecimal price;
  private String manufacturer;

  public Product() {

  }

  public Product(int id, BigDecimal price, String manufacturer) {
    this.id = id;
    this.price = price;
    this.manufacturer = manufacturer;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return id == product.id &&
        product.price.compareTo(price) == 0 &&
        manufacturer.equals(product.manufacturer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, price, manufacturer);
  }

  @Override
  public String toString() {
    return "id=" + id +
        ", price=" + price +
        ", manufacturer='" + manufacturer + '\'';
  }
}
