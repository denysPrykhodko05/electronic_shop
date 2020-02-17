package task9.com.epam.prykhodko.util;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.service.ProductService;
import java.io.BufferedWriter;
import java.util.Map;
import task9.com.epam.prykhodko.command.GetByIdProductCommand;
import task9.com.epam.prykhodko.command.GetCountCommand;
import task9.com.epam.prykhodko.entity.WriteType;

public class ServerWriterUtil {

  private final WriteType writeType;

  public ServerWriterUtil(WriteType writeType) {
    this.writeType = writeType;
  }

  public void write(Map<String, Command> map, BufferedWriter bufferedWriter, StringBuilder request, ProductService productService) {
    map.put("count", new GetCountCommand(productService, bufferedWriter, writeType));
    map.put("item", new GetByIdProductCommand(bufferedWriter, productService, request, writeType));
  }
}
