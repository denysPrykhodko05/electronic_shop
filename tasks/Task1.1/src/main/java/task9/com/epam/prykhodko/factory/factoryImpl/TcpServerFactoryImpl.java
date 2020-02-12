package task9.com.epam.prykhodko.factory.factoryImpl;

import com.epam.prykhodko.service.ProductService;
import java.net.Socket;
import task9.com.epam.prykhodko.entity.WriteType;
import task9.com.epam.prykhodko.entity.serverImpl.TcpServer;
import task9.com.epam.prykhodko.factory.ServerFactory;

public class TcpServerFactoryImpl implements ServerFactory {

  @Override
  public Runnable create(Socket s, ProductService productService, WriteType writeType) {
    return new TcpServer(s, productService, writeType);
  }
}
