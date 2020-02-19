package task9.com.epam.prykhodko.controller;

import static com.epam.prykhodko.constant.Constants.CANT_CONNECT_TO_THE_SERVER;
import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.util.readers.ConsoleHelper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class TcpClient {

  private static final Logger LOGGER = Logger.getLogger(TcpClient.class);

  public static void main(String[] args) {
    BasicConfigurator.configure();
    LOGGER.info("To exit write exit!!!");
    while (true) {
      try (Socket clientSocket = new Socket(InetAddress.getLocalHost(), 3000);
          BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
          BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
        {
          String request = ConsoleHelper.readLine();
          if ("exit".equals(request)) {
            break;
          }
          bufferedWriter.write(request);
          bufferedWriter.newLine();
          bufferedWriter.flush();

          String response = bufferedReader.readLine();
          if (Objects.isNull(response)) {
            LOGGER.info(INCORRECT_INPUT);
            continue;
          }
          LOGGER.info(response);

          bufferedWriter.flush();
        }
      } catch (IOException e) {
        LOGGER.error(CANT_CONNECT_TO_THE_SERVER);
        break;
      }
    }
  }
}