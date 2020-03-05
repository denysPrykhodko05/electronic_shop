package task9.com.epam.prykhodko.factory;

import com.epam.prykhodko.service.ProductService;
import java.net.Socket;
import task9.com.epam.prykhodko.entity.WriteType;

public interface ServerFactory {

  Runnable create(Socket s, ProductService productService, WriteType writeType);
}
