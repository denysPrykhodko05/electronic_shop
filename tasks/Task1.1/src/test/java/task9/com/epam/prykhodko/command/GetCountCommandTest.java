package task9.com.epam.prykhodko.command;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.prykhodko.service.ProductService;
import com.epam.prykhodko.service.impl.ProductServiceImpl;
import java.io.BufferedWriter;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class GetCountCommandTest {

  @Mock
  BufferedWriter bufferedWriter = mock(BufferedWriter.class);
  @Mock
  ProductService productService = mock(ProductServiceImpl.class);

  @Test
  void executeShouldExecuteAllBufferWriterCommands() throws IOException {
    GetCountCommand getCountCommand = new GetCountCommand(productService, bufferedWriter);
    getCountCommand.execute();
    verify(bufferedWriter, times(1)).write(anyString());
    verify(bufferedWriter, times(1)).newLine();
    verify(bufferedWriter, times(1)).flush();
  }
}