package task9.com.epam.prykhodko.controller;

import static com.epam.prykhodko.constant.Constants.CANT_CONNECT_TO_THE_SERVER;

import com.epam.prykhodko.util.readers.ConsoleHelper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class TcpClient {

  private static final Logger LOGGER = Logger.getLogger(TcpClient.class);

  private static void createConnection() {
    BasicConfigurator.configure();
    try (Socket clientSocket = new Socket("127.0.0.1", 3000);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

      bufferedWriter.write(ConsoleHelper.readLine());
      bufferedWriter.newLine();
      bufferedWriter.flush();

      String response = bufferedReader.readLine();
      LOGGER.info(response);

      bufferedWriter.flush();
    } catch (IOException e) {
      LOGGER.error(CANT_CONNECT_TO_THE_SERVER);
    }
  }

  public static void main(String[] args) {
    createConnection();
  }

}
