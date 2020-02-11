package task9.com.epam.prykhodko.command;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.service.ProductService;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import task9.com.epam.prykhodko.entity.WriteType;

class GetCountCommandTest {

  @Mock
  ProductService productService = mock(ProductService.class);
  @Mock
  WriteType writeType = mock(WriteType.class);

  @Test
  void executeShouldExecuteMethodOneTimes() throws IOException {
    ServerSocket serverSocket = new ServerSocket(3000);
    Socket socket = new Socket("127.0.0.1", 3000);
    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    Command command = new GetCountCommand(productService, bufferedWriter, writeType);
    command.execute();
    verify(productService, times(1)).getAll();
    verify(writeType, times(1)).writeCount(anyInt(), any(BufferedWriter.class));
  }
}