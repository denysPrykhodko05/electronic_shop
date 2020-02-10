package task9.com.epam.prykhodko.controller;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;
import static com.epam.prykhodko.constant.Constants.SERVER_STARTED;

import com.epam.prykhodko.service.ProductService;
import com.epam.prykhodko.util.readers.ConsoleHelper;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import task9.com.epam.prykhodko.entity.Handler;
import task9.com.epam.prykhodko.entity.handlerImpl.HttpHandler;
import task9.com.epam.prykhodko.entity.handlerImpl.TcpHandler;

public class ServersView implements Runnable {

  private static final Logger LOGGER = Logger.getLogger(ServersView.class);
  private final Map<String, Handler> handlers = new HashMap<>();
  private final Object monitor;
  private ProductService productService;

  public ServersView(Object monitor, ProductService productService) {
    this.monitor = monitor;
    this.productService = productService;
  }

  private void handlerInit() throws IOException {
    ServerSocket serverSocket = new ServerSocket(3000);
    TcpHandler tcpHandler = new TcpHandler(serverSocket, productService);
    HttpHandler httpHandler = new HttpHandler(serverSocket, productService);
    handlers.put("1", tcpHandler);
    handlers.put("2", httpHandler);
  }

  @Override
  public void run() {
    BasicConfigurator.configure();
    Handler handler = null;
    try {
      handlerInit();
      while (true) {
        synchronized (monitor) {
          LOGGER.info("What do you want to start? 1-TCP 2-HTTP");
          String command = ConsoleHelper.readLine();
          monitor.notify();
          handler = handlers.get(command);
        }
        if (Objects.isNull(handler)) {
          LOGGER.info(INCORRECT_INPUT);
          continue;
        }
        LOGGER.info(SERVER_STARTED);
        handler.execute();
        return;
      }
    } catch (IOException e) {
      LOGGER.error(INCORRECT_INPUT);
    }
  }
}
