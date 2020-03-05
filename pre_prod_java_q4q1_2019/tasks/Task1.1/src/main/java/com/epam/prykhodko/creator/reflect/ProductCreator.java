package com.epam.prykhodko.creator.reflect;

import com.epam.prykhodko.entity.input.InputType;
import com.epam.prykhodko.entity.products.Product;
import java.util.Locale;

public class ProductCreator extends CreatorByReflection {

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