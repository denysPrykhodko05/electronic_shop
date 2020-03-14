package com.epam.prykhodko.entity.products;

import static com.epam.prykhodko.entity.products.ProductsCategory.SMARTPHONE;

import java.math.BigDecimal;
import java.util.Objects;

public class Smartphone extends Product {

    private String modelOfTouchScreen;
    private String communicationStandard;

    public Smartphone() {
    }

    public Smartphone(Product telephone) {
        super(telephone);
    }

    public String getModelOfTouchScreen() {
        return modelOfTouchScreen;
    }

    public void setModelOfTouchScreen(String modelOfTouchScreen) {
        this.modelOfTouchScreen = modelOfTouchScreen;
    }

    public String getCommunicationStandard() {
        return communicationStandard;
    }

    public void setCommunicationStandard(String communicationStandard) {
        this.communicationStandard = communicationStandard;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }

        Smartphone that = (Smartphone) o;
        return Objects.equals(modelOfTouchScreen, that.modelOfTouchScreen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), modelOfTouchScreen);
    }

    @Override
    public String toString() {
        return super.toString() + "modelOfTouchScreen='" + modelOfTouchScreen + '\'';
    }
}
