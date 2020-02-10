package task9.com.epam.prykhodko.command;

import static com.epam.prykhodko.constant.Constants.HEADER_CORRECT;
import static com.epam.prykhodko.constant.Constants.HEADER_ERROR;
import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.service.ProductService;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;
import org.apache.log4j.Logger;

public class GetByIdProductHttpCommand implements Command {

  private static final Logger LOGGER = Logger.getLogger(GetByIdProductHttpCommand.class);
  private ProductService productService;
  private StringBuilder request;
  private BufferedWriter bufferedWriter;

  public GetByIdProductHttpCommand(BufferedWriter bufferedWriter, ProductService productService, StringBuilder request) {
    this.productService = productService;
    this.request = request;
    this.bufferedWriter = bufferedWriter;
  }

  @Override
  public void execute() {
    Product product;
    int id = Integer.parseInt(request.toString().split("=")[1]);
    product = productService.getById(id);

    try {
      if (Objects.nonNull(product)) {
        bufferedWriter.write(HEADER_CORRECT + "{manufacture:" + product.getManufacturer() + ", price:" + product.getPrice().toString() + "}");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        return;
      }
      bufferedWriter.write(HEADER_ERROR + INCORRECT_INPUT);
      bufferedWriter.newLine();
      bufferedWriter.flush();
    } catch (IOException e) {
      LOGGER.error(INCORRECT_INPUT);
    }
  }
}
