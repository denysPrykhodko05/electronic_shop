package com.epam.prykhodko.creator;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.entity.input.InputType;
import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.util.readers.ConsoleHelper;
import java.io.IOException;
import org.apache.log4j.Logger;

public class ProductCreator extends Creator {

  private static final Logger LOGGER = Logger.getLogger(ProductCreator.class);
  private boolean correctInputFlag;

  public ProductCreator(InputType inputType) {
    super(inputType);
  }

  private void setParameters(Product product) {
    while (!correctInputFlag) {
      try {
        System.out.println("Enter product id: ");
        product.setId(inputType.readInt());
        System.out.println("Enter product price: ");
        product.setPrice(inputType.readBigDecimal());
        System.out.println("Enter manufacture: ");
        String manufacture = ConsoleHelper.readLine();
        product.setManufacturer(inputType.readLine(manufacture));
      } catch (IOException | NumberFormatException e) {
        LOGGER.error(INCORRECT_INPUT);
        continue;
      }
      correctInputFlag = true;
    }
  }

  @Override
  public Product create() {
    final Product product = new Product();
    setParameters(product);
    return product;
  }

}