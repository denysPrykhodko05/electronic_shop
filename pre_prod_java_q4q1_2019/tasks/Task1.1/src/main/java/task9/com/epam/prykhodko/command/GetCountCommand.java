package task9.com.epam.prykhodko.command;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.service.ProductService;
import java.io.BufferedWriter;
import task9.com.epam.prykhodko.entity.WriteType;

public class GetCountCommand implements Command {

  private ProductService productService;
  private BufferedWriter bufferedWriter;
  private WriteType writeType;

  public GetCountCommand(ProductService productService, BufferedWriter bufferedWriter, WriteType writeType) {
    this.productService = productService;
    this.bufferedWriter = bufferedWriter;
    this.writeType = writeType;
  }

  @Override
  public void execute() {
    int size = productService.getAll().size();
    writeType.writeCount(size, bufferedWriter);
  }
}
