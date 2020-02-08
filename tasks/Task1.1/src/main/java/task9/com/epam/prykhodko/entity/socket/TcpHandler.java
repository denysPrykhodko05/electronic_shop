package task9.com.epam.prykhodko.entity.socket;

import static com.epam.prykhodko.constant.Constants.CLIENT_ACCEPTED;
import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.service.ProductService;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class TcpHandler implements Handler {

  private static final Logger LOGGER = Logger.getLogger(TcpHandler.class);
  private final ServerSocket serverSocket;
  private final ProductService productService;

  public TcpHandler(ServerSocket serverSocket, ProductService productService) {
    this.serverSocket = serverSocket;
    this.productService = productService;
  }

  public void execute() {
    BasicConfigurator.configure();
    try {
      while (true) {
        Socket s = serverSocket.accept();
        LOGGER.info(CLIENT_ACCEPTED);
        new Thread(new TcpServer(s,productService)).start();
      }
    } catch (IOException e) {
      LOGGER.error(INCORRECT_INPUT);
    }
  }
}
