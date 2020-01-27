package com.epam.prykhodko.entity.invoke.input.impl;

import com.epam.prykhodko.entity.invoke.input.InputType;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.SecureRandom;

public class RandomInput implements InputType {

  private SecureRandom random = new SecureRandom();

  @Override
  public BigDecimal readBigDecimal() throws IOException {
    return BigDecimal.valueOf(random.nextInt(2000));
  }

  @Override
  public int readInt() {
    return random.nextInt(1000);
  }

  @Override
  public String readLine() {
    return String.valueOf(random.nextInt(10000));
  }

  public String readLine(String line) {
    return line + random.nextInt(10000);
  }
}
