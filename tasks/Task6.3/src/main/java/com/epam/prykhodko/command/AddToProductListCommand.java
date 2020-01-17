package com.epam.prykhodko.command;


import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.creator.NotebookCreator;
import com.epam.prykhodko.creator.ProductCreator;
import com.epam.prykhodko.creator.SmartphoneCreator;
import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.service.ProductService;
import com.epam.prykhodko.task1.entity.Notebook;
import com.epam.prykhodko.task1.entity.Product;
import com.epam.prykhodko.task1.entity.Smartphone;
import com.epam.prykhodko.util.ConsoleHelper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class AddToProductListCommand implements Command {

  private final Logger logger = Logger.getLogger(AddToProductListCommand.class);
  private InputType inputType;
  private Product product;
  private ProductService productService;
  private Map<String, ProductCreator> creatorMap = new HashMap<>();

  public AddToProductListCommand(InputType inputType, ProductService productService) {
    this.inputType = inputType;
    this.productService = productService;
    mapInit();
  }

  private void mapInit() {
    creatorMap.put("1", new SmartphoneCreator(inputType, new Smartphone()));
    creatorMap.put("2", new NotebookCreator(inputType, new Notebook()));
  }

  @Override
  public void execute() {
    String choose = StringUtils.EMPTY;
    while (product == null) {
      try {
        System.out.println("What do you want to add: \n1-Smartphone\n2-Notebook");
        choose = ConsoleHelper.readLine();
        product = creatorMap.get(choose).create();
      } catch (IOException | NumberFormatException e) {
        logger.error(INCORRECT_INPUT);
      }
    }
    productService.add(product);
  }

}
