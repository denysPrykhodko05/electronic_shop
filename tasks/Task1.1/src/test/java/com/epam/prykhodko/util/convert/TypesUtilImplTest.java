package com.epam.prykhodko.util.convert;

import static com.epam.prykhodko.constant.Constants.STRING_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class TypesUtilImplTest {

  @Test
  void getIntShouldReturnIntegerOne() {
    int actual = TypesUtilImpl.toInt(STRING_ONE);
    int expected = INTEGER_ONE;
    assertEquals(expected,actual);
  }

  @Test
  void getBigDecimal() {
    BigDecimal actual = TypesUtilImpl.toBigDecimal(STRING_ONE);
    BigDecimal expected = new BigDecimal(STRING_ONE);
    assertEquals(expected,actual);
  }

  @Test
  void getString() {
    String actual = TypesUtilImpl.toString(STRING_ONE);
    assertEquals(STRING_ONE,actual);
  }
}