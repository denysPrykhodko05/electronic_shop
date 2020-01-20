package com.epam.prykhodko.util;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.entity.impl.ConsoleInput;
import com.epam.prykhodko.entity.impl.RandomInput;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;

public class InputUtil {

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
    mapInit();
    System.out.println("What do you want use to add products?\n1-Manually input\n2-Random input");
    Predicate<String> predicate = x -> {
      x = ConsoleScanner.readLine();
      inputType = inputMap.get(x);
      return inputType == null;
    };
    input(predicate);
    return inputType;
  }
}
