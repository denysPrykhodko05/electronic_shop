package com.epam.prykhodko.task1.entity;



public class Product {

    private int id;
    private double price;
    private String manufacturer;

    public Product(){

    }

    public Product(int id, double price, String manufacturer) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", price=" + price +
                ", manufacturer='" + manufacturer + '\'';
    }
}
