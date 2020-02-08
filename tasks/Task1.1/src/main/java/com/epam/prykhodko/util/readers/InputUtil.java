package com.epam.prykhodko.util.readers;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.entity.invoke.input.InputType;
import com.epam.prykhodko.entity.invoke.input.impl.ConsoleInput;
import com.epam.prykhodko.entity.invoke.input.impl.RandomInput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class InputUtil {

  private static final Logger LOGGER = Logger.getLogger(InputUtil.class);
  private static final Map<String, InputType> inputMap = new HashMap<>();
  private static InputType inputType;

  private static void mapInit() {
    inputMap.put("1", new ConsoleInput());
    inputMap.put("2", new RandomInput());
  }

  private static void input(Predicate<String> predicate) {
    String parameter = StringUtils.EMPTY;
    while (predicate.test(parameter)) {
      System.out.println(INCORRECT_INPUT);
      parameter = StringUtils.EMPTY;
    }
  }

  public static InputType inputType() {
    BasicConfigurator.configure();
    mapInit();
    LOGGER.info("What do you want use to add products?\n1-Manually input\n2-Random input");
    Predicate<String> predicate = x -> {
      try {
        x = ConsoleHelper.readLine();
        inputType = inputMap.get(x);
        return inputType == null;
      } catch (IOException e) {
        LOGGER.info(INCORRECT_INPUT);
      }
      return true;
    };
    input(predicate);
    return inputType;
  }
}
