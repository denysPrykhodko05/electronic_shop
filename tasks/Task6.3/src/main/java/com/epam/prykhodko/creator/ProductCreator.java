package com.epam.prykhodko.creator;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.entity.InputType;
import com.epam.prykhodko.task1.entity.ProductRepository;
import com.epam.prykhodko.util.ConsoleHelper;
import java.io.IOException;
import org.apache.log4j.Logger;

public class ProductCreator extends Creator {

  private final Logger logger = Logger.getLogger(ProductCreator.class);
  private ProductRepository product;
  private boolean correctInputFlag;

  public ProductCreator(InputType inputType, ProductRepository product) {
    super(inputType);
    this.product = product;
  }

  private void setParameters() {
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
        logger.error(INCORRECT_INPUT);
        continue;
      }
      correctInputFlag = true;
    }
  }

  @Override
  public ProductRepository create() {
    setParameters();
    return product;
  }

}
