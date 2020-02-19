package task9.com.epam.prykhodko.entity.writeImpl;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.entity.products.Product;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;
import org.apache.log4j.Logger;
import task9.com.epam.prykhodko.entity.WriteType;

public class TcpWrite implements WriteType {

  private static final Logger LOGGER = Logger.getLogger(TcpWrite.class);

  @Override
  public void writeCount(int size, BufferedWriter bufferedWriter) {
    try {
      bufferedWriter.write(String.valueOf(size));
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
        String response = product.getManufacturer() + "|" + product.getPrice().toString();
        bufferedWriter.write(response);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        return;
      }
      bufferedWriter.write(INCORRECT_INPUT);
      bufferedWriter.newLine();
      bufferedWriter.flush();
    } catch (IOException e) {
      LOGGER.error(INCORRECT_INPUT);
    }
  }
}
