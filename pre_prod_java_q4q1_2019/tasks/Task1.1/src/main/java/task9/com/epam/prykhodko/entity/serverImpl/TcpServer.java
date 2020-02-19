package task9.com.epam.prykhodko.entity.serverImpl;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.service.ProductService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import task9.com.epam.prykhodko.entity.WriteType;
import task9.com.epam.prykhodko.util.ServerWriterUtil;

public class TcpServer implements Runnable {

  private static final Logger LOGGER = Logger.getLogger(TcpServer.class);
  private final Map<String, Command> commands = new HashMap<>();
  private final WriteType writeType;
  private StringBuilder request = new StringBuilder();
  private Socket socket;
  private ProductService productService;

  public TcpServer(Socket socket, ProductService productService, WriteType writeType) {
    this.socket = socket;
    this.productService = productService;
    this.writeType = writeType;
  }

  @Override
  public void run() {
    BasicConfigurator.configure();
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
      ServerWriterUtil serverWriterUtil = new ServerWriterUtil(writeType);
      serverWriterUtil.write(commands, bufferedWriter, request, productService);
      request.append(bufferedReader.readLine());

      for (Entry<String, Command> entry : commands.entrySet()) {
        if (request.toString().contains(entry.getKey())) {
          entry.getValue().execute();
          break;
        }
      }
      request = new StringBuilder();
      bufferedWriter.flush();
      socket.close();
    } catch (IOException e) {
      LOGGER.error(INCORRECT_INPUT);
    }
  }
}