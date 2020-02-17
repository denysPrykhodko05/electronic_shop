package com.epam.prykhodko.util.convert;

import java.math.BigDecimal;

public class TypesUtilImpl {

  public static int toInt(String parameter) {
    return Integer.parseInt(parameter);
  }

  public static BigDecimal toBigDecimal(String parameter) {
    return new BigDecimal(parameter);
  }

  public static String toString(String parameter) {
    return parameter;
  }
}
