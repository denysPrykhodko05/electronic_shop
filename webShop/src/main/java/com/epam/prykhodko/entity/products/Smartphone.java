package com.epam.prykhodko.entity.products;

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

    public Smartphone(int id, String name, BigDecimal price, String manufacturer, String communicationStandard,
        String modelOfTouchScreen, String description) {
        super(id, name, price, manufacturer, "smartphone", description);
        this.modelOfTouchScreen = modelOfTouchScreen;
        this.communicationStandard = communicationStandard;
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
