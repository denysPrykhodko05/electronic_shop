package com.epam.prykhodko.entity.products;

import static com.epam.prykhodko.entity.products.ProductsCategory.NOTEBOOK;

import java.math.BigDecimal;
import java.util.Objects;

public class Notebook extends Product {

    private String modelOfTouchpad;

    public Notebook() {
    }

    public Notebook(Product notebook) {
        super(notebook);
    }

    public String getModelOfTouchpad() {
        return modelOfTouchpad;
    }

    public void setModelOfTouchpad(String modelOfTouchpad) {
        this.modelOfTouchpad = modelOfTouchpad;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }

        Notebook notebook = (Notebook) o;
        return Objects.equals(modelOfTouchpad, notebook.modelOfTouchpad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelOfTouchpad);
    }

    @Override
    public String toString() {
        return super.toString() + "modelOfTouchpad='" + modelOfTouchpad + '\'';
    }
}
