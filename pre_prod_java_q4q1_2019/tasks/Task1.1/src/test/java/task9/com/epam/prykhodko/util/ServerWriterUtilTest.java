package task9.com.epam.prykhodko.util;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.prykhodko.repository.impl.ProductRepositoryImpl;
import com.epam.prykhodko.service.impl.ProductServiceImpl;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import task9.com.epam.prykhodko.entity.WriteType;
import task9.com.epam.prykhodko.entity.writeImpl.JsonWrite;

class ServerWriterUtilTest {

  @Mock
  Map map = mock(HashMap.class);

  @Test
  void write() throws IOException {
    new ServerSocket(3000);
    ServerWriterUtil serverWriterUtil = new ServerWriterUtil(new JsonWrite());
    serverWriterUtil.write(map, new BufferedWriter(new OutputStreamWriter(new Socket("127.0.0.1", 3000).getOutputStream())), new StringBuilder(),
        new ProductServiceImpl(new ProductRepositoryImpl()));
    verify(map, times(2)).put(anyString(), any(WriteType.class));
  }
}