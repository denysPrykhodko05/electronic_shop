package com.epam.prykhodko.entity.input;

import java.io.IOException;
import java.math.BigDecimal;

public interface InputType {

  BigDecimal readBigDecimal() throws IOException;

  int readInt();

  String readLine() throws IOException;

  String readLine(String line) throws IOException;
}
