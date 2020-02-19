package task9.com.epam.prykhodko.entity.writeImpl;

import static com.epam.prykhodko.constant.Constants.BAD_REQUEST;
import static com.epam.prykhodko.constant.Constants.HEADER_CORRECT;
import static com.epam.prykhodko.constant.Constants.HEADER_ERROR_INCORRECT_ID;
import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.entity.products.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import task9.com.epam.prykhodko.entity.WriteType;

public class JsonWrite implements WriteType {

  private static final Logger LOGGER = Logger.getLogger(JsonWrite.class);

  @Override
  public void writeCount(int size, BufferedWriter bufferedWriter) {
    try {
      JSONObject json = new JSONObject();
      json.put("count", size);
      bufferedWriter.write(HEADER_CORRECT + json.toString());
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
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String response = gson.toJson(product);
        bufferedWriter.write(HEADER_CORRECT + response);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        return;
      }
      bufferedWriter.write(HEADER_ERROR_INCORRECT_ID + BAD_REQUEST);
      bufferedWriter.newLine();
      bufferedWriter.flush();
    } catch (IOException e) {
      LOGGER.error(INCORRECT_INPUT);
    }
  }
}
