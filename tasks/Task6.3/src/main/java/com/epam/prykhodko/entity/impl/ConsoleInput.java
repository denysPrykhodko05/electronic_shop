package com.epam.prykhodko.entity.impl;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.util.ConsoleHelper;
import java.io.IOException;
import java.math.BigDecimal;
import org.apache.log4j.Logger;

public class ConsoleInput implements InputType {

  Logger logger = Logger.getLogger(ConsoleInput.class);

  @Override
  public int readInt() {
    try {
      return ConsoleHelper.readInt();
    } catch (IOException e) {
        logger.error(INCORRECT_INPUT);
    }
    return -1;
  }

  @Override
  public String readLine() throws IOException {
    return ConsoleHelper.readLine();
  }

  @Override
  public String readLine(String line) throws IOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public BigDecimal readBigDecimal() {
    return new BigDecimal(readInt());
  }
}
