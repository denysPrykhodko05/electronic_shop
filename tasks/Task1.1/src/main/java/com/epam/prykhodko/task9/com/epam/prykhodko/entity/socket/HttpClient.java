package com.epam.prykhodko.task9.com.epam.prykhodko.entity.socket;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.task9.com.epam.prykhodko.util.EntityInit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

public class HttpClient implements Runnable {

  private static final Logger LOGGER = Logger.getLogger(HttpClient.class);
  private final Map<String, Command> commands = new HashMap<>();
  private StringBuilder request = new StringBuilder();
  private Socket s;
  private InputStream is;
  private OutputStream os;

  public HttpClient(Socket s) throws IOException {
    this.s = s;
    this.is = s.getInputStream();
    this.os = s.getOutputStream();
  }

  public void run() {
    try {
      EntityInit.entityInit(commands, new BufferedWriter(new OutputStreamWriter(os)), request);
      readInputHeaders();
      writeResponse();
    } catch (IOException t) {
      LOGGER.error(INCORRECT_INPUT);
    } finally {
      try {
        s.close();
      } catch (Throwable t) {
        LOGGER.error(INCORRECT_INPUT);
      }
    }
    System.err.println("Client processing finished");
  }

  private void writeResponse() {
    for (Entry<String, Command> entry : commands.entrySet()) {
      if (request.toString().contains(entry.getKey())) {
        entry.getValue().execute();
        break;
      }
    }
  }

  private void readInputHeaders() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    String s = br.readLine();
    Pattern pattern = Pattern.compile("=[\\w+1-9]");
    Matcher matcherWithEqual = pattern.matcher(s);
    if (matcherWithEqual.find()) {
      request.append("item").append(matcherWithEqual.group());
      return;
    }
    Pattern patternCount = Pattern.compile("(?<=/)\\w+(?=\\s|\\?)");
    Matcher matcher = patternCount.matcher(s);
    if (matcher.find()) {
      request.append(matcher.group());
    }
  }
}
