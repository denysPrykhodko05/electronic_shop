package task9.com.epam.prykhodko.command;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.entity.products.Product;
import com.epam.prykhodko.service.ProductService;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import task9.com.epam.prykhodko.entity.WriteType;

class GetByIdProductCommandTest {

  @Mock
  WriteType writeType = mock(WriteType.class);
  @Mock
  ProductService productService = mock(ProductService.class);
  @Test
  void executeWriteShouldExecuteOneTime() throws IOException {
    ServerSocket serverSocket = new ServerSocket(3000);
    StringBuilder request = new StringBuilder();
    request.append("get item=1");
    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new Socket("127.0.0.1", 3000).getOutputStream()));
    Command command = new GetByIdProductCommand(bufferedWriter, productService, request, writeType);
    command.execute();
    verify(productService, times(1)).getById(anyInt());
    verify(writeType, times(1)).writeProduct(any(Product.class), any(BufferedWriter.class));
  }
}