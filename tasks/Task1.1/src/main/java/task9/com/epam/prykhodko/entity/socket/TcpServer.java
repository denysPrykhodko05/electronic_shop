package task9.com.epam.prykhodko.entity.socket;

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
import task9.com.epam.prykhodko.util.EntityInit;

public class TcpServer implements Server {

  private static final Logger LOGGER = Logger.getLogger(TcpServer.class);
  private final Map<String, Command> commands = new HashMap<>();
  private StringBuilder request = new StringBuilder();
  private Socket socket;
  private ProductService productService;

  public TcpServer(Socket socket, ProductService productService) {
    this.socket = socket;
    this.productService = productService;
  }

  public void createConnection() {
    BasicConfigurator.configure();
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
      while (true) {

        EntityInit entityInit = new EntityInit();
        entityInit.entityInit(commands, bufferedWriter, request, productService);
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
      }
    } catch (IOException e) {
      LOGGER.error(INCORRECT_INPUT);
    }
  }

  @Override
  public void run() {
    createConnection();
  }
}