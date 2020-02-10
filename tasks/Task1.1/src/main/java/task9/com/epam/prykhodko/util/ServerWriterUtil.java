package task9.com.epam.prykhodko.util;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.service.ProductService;
import java.io.BufferedWriter;
import java.util.Map;
import task9.com.epam.prykhodko.command.GetByIdProductCommand;
import task9.com.epam.prykhodko.command.GetByIdProductHttpCommand;
import task9.com.epam.prykhodko.command.GetCountByHttpCommand;
import task9.com.epam.prykhodko.command.GetCountCommand;

public class ServerWriterUtil {

  public void writeToTcp(Map<String, Command> map, BufferedWriter bufferedWriter, StringBuilder request, ProductService productService) {
    map.put("count", new GetCountCommand(productService, bufferedWriter));
    map.put("item", new GetByIdProductCommand(bufferedWriter, productService, request));
  }

  public void writeByJson(Map<String, Command> map, BufferedWriter bufferedWriter, StringBuilder request, ProductService productService) {
    map.put("count", new GetCountByHttpCommand(productService, bufferedWriter));
    map.put("item", new GetByIdProductHttpCommand(bufferedWriter, productService, request));
  }

}
