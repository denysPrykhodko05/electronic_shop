package com.epam.prykhodko.command.impl;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;
import static com.epam.prykhodko.constant.Constants.STRING_ZERO;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.creator.reflect.NotebookCreator;
import com.epam.prykhodko.creator.reflect.ProductCreator;
import com.epam.prykhodko.creator.reflect.SmartphoneCreator;
import com.epam.prykhodko.entity.invoke.input.InputType;
import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.service.ProductService;
import com.epam.prykhodko.util.readers.ConsoleHelper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.log4j.Logger;

public class AddToProductListByReflectCommand implements Command {

  private static final Logger LOGGER = Logger.getLogger(AddToProductListByReflectCommand.class);
  private final InputType inputType;
  private final Locale locale;
  private final ProductService productService;
  private Map<String, ProductCreator> creatorMap = new HashMap<>();

  public AddToProductListByReflectCommand(InputType inputType, Locale locale,
      ProductService productService) {
    this.inputType = inputType;
    this.locale = locale;
    this.productService = productService;
    mapInit();
  }

  @Override
  public void execute() {
    Product product = null;
    boolean createFlag = false;
    String choose;
    while (!createFlag) {
      try {
        LOGGER.info("What do you want to add: \n0-Stop input\n1-Smartphone\n2-Notebook");
        choose = ConsoleHelper.readLine();
        if (STRING_ZERO.equals(choose)) {
          return;
        }
        ProductCreator productCreator = creatorMap.get(choose);
        if (productCreator == null) {
          LOGGER.error(INCORRECT_INPUT);
          continue;
        }
        product = productCreator.create();
        createFlag = true;
      } catch (IOException | NumberFormatException e) {
        LOGGER.error(INCORRECT_INPUT);
        createFlag = false;
      }
    }
    productService.add(product);
  }

  private void mapInit() {
    creatorMap.put("1", new SmartphoneCreator(inputType, locale));
    creatorMap.put("2", new NotebookCreator(inputType, locale));
  }
}
