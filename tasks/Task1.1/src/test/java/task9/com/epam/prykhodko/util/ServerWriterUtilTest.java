package task9.com.epam.prykhodko.util;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.repository.impl.ProductRepositoryImpl;
import com.epam.prykhodko.service.impl.ProductServiceImpl;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class ServerWriterUtilTest {

  @Mock
  Map<String, Command> map = mock(HashMap.class);

  ServerSocket serverSocket;

  @BeforeEach
  void setUp() throws IOException {
    serverSocket = new ServerSocket(3000);
  }

  @Test
  void entityInitMapShouldAddTwoCommands() throws IOException {
    ServerWriterUtil serverWriterUtil = new ServerWriterUtil();
    serverWriterUtil.writeToTcp(map, new BufferedWriter(new OutputStreamWriter(new Socket("127.0.0.1", 3000).getOutputStream())), new StringBuilder(),
        new ProductServiceImpl(new ProductRepositoryImpl()));
    verify(map, times(2)).put(anyString(), any(Command.class));
  }

  @Test
  void writeByJsonMapShouldAddTwoCommands() throws IOException {
    ServerWriterUtil serverWriterUtil = new ServerWriterUtil();
    serverWriterUtil.writeByJson(map, new BufferedWriter(new OutputStreamWriter(new Socket("127.0.0.1", 3000).getOutputStream())), new StringBuilder(),
        new ProductServiceImpl(new ProductRepositoryImpl()));
    verify(map, times(2)).put(anyString(), any(Command.class));
  }
}