package com.epam.prykhodko.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Smartphone extends Telephone {

    private String modelOfTouchScreen;

    public Smartphone() {
    }

    public Smartphone(int id, BigDecimal price, String manufacturer, String communicationStandard, String modelOfTouchScreen) {
        super(id, price, manufacturer, communicationStandard);
        this.modelOfTouchScreen = modelOfTouchScreen;
    }

    public String getModelOfTouchScreen() {
        return modelOfTouchScreen;
    }

    public void setModelOfTouchScreen(String modelOfTouchScreen) {
        this.modelOfTouchScreen = modelOfTouchScreen;
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
