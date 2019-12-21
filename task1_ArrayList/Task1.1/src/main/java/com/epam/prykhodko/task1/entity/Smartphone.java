package com.epam.prykhodko.task1.entity;

import java.util.Objects;

public class Smartphone extends Telephone {

    private String modelOfTouchScreen;

    public Smartphone() {
    }

    public Smartphone(int id, double price, String manufacturer, String communicationStandard, String modelOfTouchScreen) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Smartphone that = (Smartphone) o;
        return Objects.equals(modelOfTouchScreen, that.modelOfTouchScreen);
    }

    @Override
    public String toString() {
        return "modelOfTouchScreen='" + modelOfTouchScreen + '\'';
    }
}
