package com.epam.prykhodko.task9.com.epam.prykhodko.entity.socket;

import static com.epam.prykhodko.constant.Constants.CLIENT_ACCEPTED;
import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.Logger;

public class HttpServer {

  private static final Logger LOGGER = Logger.getLogger(HttpServer.class);

  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(3000);
      while (true) {
        Socket s = serverSocket.accept();
        LOGGER.info(CLIENT_ACCEPTED);
        new Thread(new HttpClient(s)).start();
      }
    } catch (IOException e) {
      LOGGER.error(INCORRECT_INPUT);
    }
  }
}
