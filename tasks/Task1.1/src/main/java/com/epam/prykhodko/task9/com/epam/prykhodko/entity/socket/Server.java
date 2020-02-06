package com.epam.prykhodko.task9.com.epam.prykhodko.entity.socket;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.task9.com.epam.prykhodko.util.EntityInit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Server {

  private static final Logger LOGGER = Logger.getLogger(Server.class);
  private static final Map<String, Command> commands = new HashMap();
  private static StringBuilder request = new StringBuilder();

  /*private static void entityInit(Map<String, Command> map, BufferedWriter bufferedWriter) {
    Smartphone smartphone = new Smartphone();
    Notebook notebook = new Notebook();

    smartphone.setId(1);
    smartphone.setManufacturer("Apple");
    smartphone.setPrice(new BigDecimal(999));
    smartphone.setCommunicationStandard("4G");
    smartphone.setModelOfTouchScreen("Apple");

    notebook.setId(2);
    notebook.setManufacturer("Asus");
    notebook.setPrice(new BigDecimal(1000));
    notebook.setModelOfTouchpad("Asus");

    ProductRepository productRepository = new ProductRepositoryImpl();
    productService = new ProductServiceImpl(productRepository);
    productService.add(smartphone);
    productService.add(notebook);
    map.put("get count", new GetCountCommand(productService, bufferedWriter));
    map.put("get item", new GetByIdProductCommand(bufferedWriter, productService, request));
  }*/

  private static void createConnection() {
    BasicConfigurator.configure();
    try {
      ServerSocket serverSocket = new ServerSocket(3000);
      while (true) {
        Socket clientSocket = serverSocket.accept();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        EntityInit.entityInit(commands, bufferedWriter, request);

        request.append(bufferedReader.readLine());

        for (Entry<String, Command> entry : commands.entrySet()) {
          if (request.toString().contains(entry.getKey())) {
            entry.getValue().execute();
            break;
          }
        }

        request = new StringBuilder();
        bufferedWriter.flush();
        bufferedReader.close();
        bufferedWriter.close();
        clientSocket.close();
      }
    } catch (IOException e) {
      LOGGER.error(INCORRECT_INPUT);
    }

  }

  public static void main(String[] args) {
    createConnection();
  }
}