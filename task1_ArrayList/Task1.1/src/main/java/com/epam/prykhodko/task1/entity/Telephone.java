package com.epam.prykhodko.task1.entity;

public class Telephone extends Product {

    private String communicationStandard;

    public Telephone(){

    }

    public Telephone(int id, double price, String manufacturer, String communicationStandard) {
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
    public String toString() {
        return "communicationStandard='" + communicationStandard + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telephone telephone = (Telephone) o;
        return communicationStandard.equals(telephone.communicationStandard);
    }
}
