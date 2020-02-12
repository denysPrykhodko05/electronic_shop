package task9.com.epam.prykhodko.factory.factoryImpl;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.service.ProductService;
import java.io.IOException;
import java.net.Socket;
import org.apache.log4j.Logger;
import task9.com.epam.prykhodko.entity.WriteType;
import task9.com.epam.prykhodko.entity.serverImpl.HttpServer;
import task9.com.epam.prykhodko.factory.ServerFactory;

public class HttpServerFactoryImpl implements ServerFactory {

  private static final Logger LOGGER = Logger.getLogger(HttpServerFactoryImpl.class);

  @Override
  public Runnable create(Socket s, ProductService productService, WriteType writeType) {
    try {
      return new HttpServer(s, productService, writeType);
    } catch (IOException e) {
      LOGGER.info(INCORRECT_INPUT);
    }
    return null;
  }
}
