package com.epam.prykhodko.controller;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.entities.ProductType;
import com.epam.prykhodko.entities.impl.AddNotebookCommand;
import com.epam.prykhodko.entities.impl.AddSmartphoneCommand;
import com.epam.prykhodko.strategies.InputType;
import com.epam.prykhodko.strategies.impl.ConsoleInput;
import com.epam.prykhodko.strategies.impl.RandomInput;
import com.epam.prykhodko.util.ConsoleScanner;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;

public class AddProductController {

  private static final Map<String, ProductType> productMap = new HashMap<>();
  private static final Map<String, InputType> inputMap = new HashMap<>();
  private static ProductType product;
  private static InputType inputType;

  public static void main(String[] args) {
    mapInit();
    System.out.println("What do you want use?\n1-Manually input\n2-Random input");
    inputType();
    productInput();
  }

  private static void input(Predicate<String> predicate) {
    String parameter = StringUtils.EMPTY;
    while (predicate.test(parameter)) {
      System.out.println(INCORRECT_INPUT);
      parameter = StringUtils.EMPTY;
    }
  }

  private static void productInput() {
    while (true) {
      System.out.println("What do you want add?\n1-Smartphone\n2-Notebook");
      Predicate<String> predicate = x -> {
        x = ConsoleScanner.readLine();
        product = productMap.getOrDefault(x, null);
        return product == null;
      };
      input(predicate);

      inputType.setProduct(product.get());
      inputType.input();
    }
  }

  private static void inputType() {
    Predicate<String> predicate = x -> {
      x = ConsoleScanner.readLine();
      inputType = inputMap.getOrDefault(x, null);
      return inputType == null;
    };
    input(predicate);
  }

  private static void mapInit() {
    productMap.put("1", new AddSmartphoneCommand());
    productMap.put("2", new AddNotebookCommand());
    inputMap.put("1", new ConsoleInput());
    inputMap.put("2", new RandomInput());
  }
}
