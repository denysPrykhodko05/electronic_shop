package task9.com.epam.prykhodko.entity.writeImpl;

import static com.epam.prykhodko.constant.Constants.HEADER_CORRECT;
import static com.epam.prykhodko.constant.Constants.HEADER_ERROR;
import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.entity.products.Product;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;
import org.apache.log4j.Logger;
import task9.com.epam.prykhodko.entity.WriteType;

public class JsonWrite implements WriteType {

  private static final Logger LOGGER = Logger.getLogger(JsonWrite.class);

  @Override
  public void writeCount(int size, BufferedWriter bufferedWriter) {
    try {
      bufferedWriter.write(HEADER_CORRECT + "{count:" + size + "}");
      bufferedWriter.newLine();
      bufferedWriter.flush();
    } catch (IOException e) {
      LOGGER.error(INCORRECT_INPUT);
    }
  }

  @Override
  public void writeProduct(Product product, BufferedWriter bufferedWriter) {
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
