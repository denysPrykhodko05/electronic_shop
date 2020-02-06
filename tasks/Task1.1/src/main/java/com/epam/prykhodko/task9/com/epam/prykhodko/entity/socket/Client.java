package com.epam.prykhodko.task9.com.epam.prykhodko.entity.socket;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.util.readers.ConsoleHelper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Client {

  private static final Logger LOGGER = Logger.getLogger(Client.class);

  private static void createConnection() {
    BasicConfigurator.configure();
    try {
      Socket clientSocket = new Socket("127.0.0.1", 3000);
      BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

      bufferedWriter.write(ConsoleHelper.readLine());
      bufferedWriter.newLine();
      bufferedWriter.flush();

      String response = bufferedReader.readLine();
      LOGGER.info(response);

      bufferedWriter.flush();
      bufferedWriter.close();
      bufferedReader.close();
      clientSocket.close();
    } catch (IOException e) {
      LOGGER.error(INCORRECT_INPUT);
    }
  }

  public static void main(String[] args) {
    createConnection();
  }
}
