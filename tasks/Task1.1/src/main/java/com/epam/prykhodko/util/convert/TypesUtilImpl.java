package com.epam.prykhodko.util.convert;

import java.math.BigDecimal;

public class TypesUtilImpl {

  public static int getInt(String parameter) {
    return Integer.parseInt(parameter);
  }

  public static BigDecimal getBigDecimal(String parameter) {
    return new BigDecimal(parameter);
  }

  public static String getString(String parameter) {
    return parameter;
  }
}
