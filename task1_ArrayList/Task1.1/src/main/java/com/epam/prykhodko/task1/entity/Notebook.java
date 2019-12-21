package com.epam.prykhodko.task1.entity;

import java.util.Objects;

public class Notebook extends Product {

    private String modelOfTouchpad;

    public Notebook() {
    }

    public Notebook(int id, double price, String manufacturer, String modelOfTouchpad) {
        super(id, price, manufacturer);
        this.modelOfTouchpad=modelOfTouchpad;
    }

    public String getModelOfTouchpad() {
        return modelOfTouchpad;
    }

    public void setModelOfTouchpad(String modelOfTouchpad) {
        this.modelOfTouchpad = modelOfTouchpad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notebook notebook = (Notebook) o;
        return Objects.equals(modelOfTouchpad, notebook.modelOfTouchpad);
    }

    @Override
    public String toString() {
        return "modelOfTouchpad='" + modelOfTouchpad + '\'';
    }
}
