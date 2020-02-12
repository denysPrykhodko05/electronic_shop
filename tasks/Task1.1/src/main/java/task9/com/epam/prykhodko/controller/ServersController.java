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
import task9.com.epam.prykhodko.entity.WriteType;
import task9.com.epam.prykhodko.entity.handlerImpl.ServerHandler;
import task9.com.epam.prykhodko.entity.writeImpl.JsonWrite;
import task9.com.epam.prykhodko.entity.writeImpl.TcpWrite;
import task9.com.epam.prykhodko.factory.ServerFactory;
import task9.com.epam.prykhodko.factory.factoryImpl.HttpServerFactoryImpl;
import task9.com.epam.prykhodko.factory.factoryImpl.TcpServerFactoryImpl;

public class ServersController implements Runnable {

  private static final Logger LOGGER = Logger.getLogger(ServersController.class);
  private final Map<String, ServerFactory> factory = new HashMap<>();
  private final Map<String, WriteType> writers = new HashMap<>();
  private final Object monitor;
  private ProductService productService;

  public ServersController(Object monitor, ProductService productService) {
    this.monitor = monitor;
    this.productService = productService;
  }

  private void factoryMapInit() {
    ServerFactory httpServerFactory = new HttpServerFactoryImpl();
    ServerFactory tcpServerFactory = new TcpServerFactoryImpl();
    factory.put("1", tcpServerFactory);
    factory.put("2", httpServerFactory);
  }

  private void writeTypeInit() {
    writers.put("1", new TcpWrite());
    writers.put("2", new JsonWrite());
  }

  @Override
  public void run() {
    BasicConfigurator.configure();
    Handler handler;
    ServerFactory serverFactory;
    try (ServerSocket serverSocket = new ServerSocket(3000)) {
      writeTypeInit();
      factoryMapInit();
      while (true) {
        String command;
        synchronized (monitor) {
          LOGGER.info("What do you want to start? 1-TCP 2-HTTP");
          command = ConsoleHelper.readLine();
          monitor.notify();
          serverFactory = factory.get(command);
          handler = new ServerHandler(serverSocket, productService);
        }
        if (Objects.isNull(serverFactory)) {
          LOGGER.info(INCORRECT_INPUT);
          continue;
        }
        WriteType writeType = writers.get(command);
        handler.setWriteType(writeType);
        handler.setServerFactory(serverFactory);
        LOGGER.info(SERVER_STARTED);
        handler.execute();
        return;
      }
    } catch (IOException e) {
      LOGGER.error(INCORRECT_INPUT);
    }
  }
}
