package com.epam.prykhodko.creator;

import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.task1.entity.Product;
import java.util.Locale;

public class ProductCreator extends CreatorAnno {

  public ProductCreator(InputType inputType, Locale locale) {
    super(inputType, locale);
  }

  @Override
  public Product create() {
    final Product product = new Product();
    setParameters(product);
    return product;
  }
}