package task9.com.epam.prykhodko.entity.handlerImpl;

import static com.epam.prykhodko.constant.Constants.CLIENT_ACCEPTED;
import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.service.ProductService;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import task9.com.epam.prykhodko.entity.Handler;
import task9.com.epam.prykhodko.entity.serverImpl.HttpServer;

public class HttpHandler implements Handler {

  private static final Logger LOGGER = Logger.getLogger(HttpHandler.class);
  private final ServerSocket serverSocket;
  private ProductService productService;

  public HttpHandler(ServerSocket serverSocket, ProductService productService) {
    this.serverSocket = serverSocket;
    this.productService = productService;
  }

  public void execute() {
    BasicConfigurator.configure();
    try {
      while (true) {
        Socket s = serverSocket.accept();
        LOGGER.info(CLIENT_ACCEPTED);
        new Thread(new HttpServer(s, productService)).start();
      }
    } catch (IOException e) {
      LOGGER.error(INCORRECT_INPUT);
    }
  }

}
