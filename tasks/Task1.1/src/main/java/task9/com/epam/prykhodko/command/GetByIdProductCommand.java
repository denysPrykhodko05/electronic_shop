package task9.com.epam.prykhodko.command;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.service.ProductService;
import java.io.BufferedWriter;
import task9.com.epam.prykhodko.entity.WriteType;

public class GetByIdProductCommand implements Command {

  private final WriteType writeType;
  private ProductService productService;
  private StringBuilder request;
  private BufferedWriter bufferedWriter;

  public GetByIdProductCommand(BufferedWriter bufferedWriter, ProductService productService, StringBuilder request, WriteType writeType) {
    this.productService = productService;
    this.request = request;
    this.bufferedWriter = bufferedWriter;
    this.writeType = writeType;
  }

  @Override
  public void execute() {
    Product product;
    int id = Integer.parseInt(request.toString().split("=")[1]);
    product = productService.getById(id);
    writeType.writeProduct(product,bufferedWriter);
  }
}
