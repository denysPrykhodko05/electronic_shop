package task9.com.epam.prykhodko.command;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.prykhodko.entity.products.Notebook;
import com.epam.prykhodko.service.ProductService;
import com.epam.prykhodko.service.impl.ProductServiceImpl;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class GetByIdProductHttpCommandTest {


  @Mock
  BufferedWriter bufferedWriter = mock(BufferedWriter.class);
  @Mock
  ProductService productService = mock(ProductServiceImpl.class);
  @Mock
  StringBuilder stringBuilder = new StringBuilder();

  @Test
  void executeShouldExecuteCodeWithIncorrectInput() throws IOException {
    stringBuilder.append("get item=1");
    GetByIdProductHttpCommand command = new GetByIdProductHttpCommand(bufferedWriter, productService, stringBuilder);
    command.execute();
    verify(productService, times(1)).getById(anyInt());
    verify(bufferedWriter, times(1)).write(anyString());
    verify(bufferedWriter, times(1)).newLine();
    verify(bufferedWriter, times(1)).flush();
  }

  @Test
  void executeShouldFindPrintProduct() throws IOException {
    stringBuilder.append("get item=1");
    GetByIdProductHttpCommand command = new GetByIdProductHttpCommand(bufferedWriter, productService, stringBuilder);
    when(productService.getById(anyInt())).thenReturn(new Notebook(1, new BigDecimal(1000), "asus", "asus"));
    command.execute();
    verify(productService, times(1)).getById(anyInt());
    verify(bufferedWriter, times(1)).write(anyString());
    verify(bufferedWriter, times(1)).newLine();
    verify(bufferedWriter, times(1)).flush();
  }
}