package com.epam.prykhodko.command;

import commandInterface.Command;
import java.util.HashMap;
import java.util.Map;

public class ShopInvoker {

  private Map<Integer, Command> commandMap;
  private Command showAllProducts;
  private Command addProduct;

  ShopInvoker(Command showAllProducts, Command addProduct, Command showBucket) {
    commandMap = new HashMap<>();
    this.showAllProducts = showAllProducts;
    this.addProduct = addProduct;
  }

  public void showAllProducts() {
    showAllProducts.execute();
  }

  public void addProduct() {
    addProduct.execute();
  }

}
