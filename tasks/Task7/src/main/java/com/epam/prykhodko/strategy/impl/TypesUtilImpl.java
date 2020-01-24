package com.epam.prykhodko.strategy.impl;

import com.epam.prykhodko.strategy.TypesUtil;
import java.math.BigDecimal;

public class TypesUtilImpl implements TypesUtil {

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
