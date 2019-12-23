package com.epam.prykhodko.task1.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Telephone extends Product {

    private String communicationStandard;

    public Telephone() {

    }

    public Telephone(int id, BigDecimal price, String manufacturer, String communicationStandard) {
        super(id, price, manufacturer);
        this.communicationStandard = communicationStandard;
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
        Telephone telephone = (Telephone) o;
        return communicationStandard.equals(telephone.communicationStandard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), communicationStandard);
    }

    @Override
    public String toString() {
        return super.toString() + "communicationStandard='" + communicationStandard + '\'';
    }

}
