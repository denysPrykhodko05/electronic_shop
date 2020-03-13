package com.epam.prykhodko.entity.products;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Product implements Serializable {

    private int id;
    private String name;
    private String manufacturer;
    private BigDecimal price;
    private String category;
    private String description;

    public Product() {

    }

    public Product(Product product) {
        this.id = product.id;
        this.price = product.price;
        this.manufacturer = product.manufacturer;
        this.name = product.name;
        this.category = product.category;
    }

    public Product(int id, String name, BigDecimal price, String manufacturer, String category, String description) {
        this.id = id;
        this.price = price;
        this.manufacturer = manufacturer;
        this.name = name;
        this.category = category;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return id == product.id
            && product.price.compareTo(price) == 0
            && manufacturer.equals(product.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, manufacturer);
    }

    @Override
    public String toString() {
        return "id=" + id
            + ", price=" + price
            + ", manufacturer='" + manufacturer + '\'';
    }
}
