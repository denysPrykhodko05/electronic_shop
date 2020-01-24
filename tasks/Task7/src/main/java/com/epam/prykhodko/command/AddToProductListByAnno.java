package com.epam.prykhodko.command;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.creator.NotebookCreator;
import com.epam.prykhodko.creator.ProductCreator;
import com.epam.prykhodko.creator.SmartphoneCreator;
import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.service.ProductService;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.util.ConsoleHelper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class AddToProductListByAnno implements Command {

  private static final Logger LOGGER = Logger.getLogger(AddToProductListByAnno.class);
  private InputType inputType;
  private Locale locale;
  private ProductService productService;
  private Product product;
  private Map<String, ProductCreator> creatorMap = new HashMap<>();

  public AddToProductListByAnno(InputType inputType, Locale locale,
      ProductService productService) {
    this.inputType = inputType;
    this.locale = locale;
    this.productService = productService;
    mapInit();
  }

  @Override
  public void execute() {
    String choose = StringUtils.EMPTY;
    while (Objects.isNull(product)) {
      try {
        System.out.println("What do you want to add: \n1-Smartphone\n2-Notebook");
        choose = ConsoleHelper.readLine();
        product = creatorMap.get(choose).create();
      } catch (IOException | NumberFormatException e) {
        LOGGER.error(INCORRECT_INPUT);
      }
    }
    productService.add(product);
    product = null;
  }

  private void mapInit() {
    creatorMap.put("1", new SmartphoneCreator(inputType, locale));
    creatorMap.put("2", new NotebookCreator(inputType, locale));
  }
}
