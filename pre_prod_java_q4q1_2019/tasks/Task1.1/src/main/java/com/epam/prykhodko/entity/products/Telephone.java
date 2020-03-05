package com.epam.prykhodko.entity.products;

import com.epam.prykhodko.annotation.Description;
import java.math.BigDecimal;
import java.util.Objects;

public class Telephone extends Product {

  @Description(title = "communication_standard")
  private String communicationStandard;

  public Telephone() {

  }

  public Telephone(Product telephone) {
    super(telephone);
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
