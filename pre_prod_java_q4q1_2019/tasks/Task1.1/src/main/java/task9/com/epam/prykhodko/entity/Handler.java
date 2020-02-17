package task9.com.epam.prykhodko.entity;

import task9.com.epam.prykhodko.factory.ServerFactory;

public interface Handler {

  void execute();

  void setWriteType(WriteType writeType);

  public void setServerFactory(ServerFactory serverFactory);
}
