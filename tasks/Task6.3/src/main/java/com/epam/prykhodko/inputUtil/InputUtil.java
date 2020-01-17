package com.epam.prykhodko.inputUtil;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.entity.impl.ConsoleInput;
import com.epam.prykhodko.entity.impl.RandomInput;
import com.epam.prykhodko.util.ConsoleScanner;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;

public class InputUtil {

  private final Map<String, InputType> inputMap = new HashMap<>();
  private InputType inputType;

  private void mapInit() {
    inputMap.put("1", new ConsoleInput());
    inputMap.put("2", new RandomInput());
  }

  private void input(Predicate<String> predicate) {
    String parameter = StringUtils.EMPTY;
    while (predicate.test(parameter)) {
      System.out.println(INCORRECT_INPUT);
      parameter = StringUtils.EMPTY;
    }
  }

  public InputType inputType() {
    mapInit();
    System.out.println("What do you want use to add products?\n1-Manually input\n2-Random input");
    Predicate<String> predicate = x -> {
      x = ConsoleScanner.readLine();
      inputType = inputMap.getOrDefault(x, null);
      return inputType == null;
    };
    input(predicate);
    return inputType;
  }
}
