package task9.com.epam.prykhodko.command;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.service.ProductService;
import java.io.BufferedWriter;
import java.io.IOException;
import org.apache.log4j.Logger;

public class GetCountCommand implements Command {

  private static final Logger LOGGER = Logger.getLogger(GetCountCommand.class);
  private ProductService productService;
  private BufferedWriter bufferedWriter;

  public GetCountCommand(ProductService productService, BufferedWriter bufferedWriter) {
    this.productService = productService;
    this.bufferedWriter = bufferedWriter;
  }

  @Override
  public void execute() {
    int size = productService.getAll().size();
    try {

      bufferedWriter.write(String.valueOf(size));
      bufferedWriter.newLine();
      bufferedWriter.flush();
    } catch (IOException e) {
      LOGGER.error(INCORRECT_INPUT);
    }
  }
}
