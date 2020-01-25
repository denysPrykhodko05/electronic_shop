package entity;

import java.math.BigDecimal;

public interface ProductInterface {

  int getId();

  void setId(int id);

  BigDecimal getPrice();

  void setPrice(BigDecimal price);

  String getManufacturer();

  void setManufacturer(String manufacturer);

}
