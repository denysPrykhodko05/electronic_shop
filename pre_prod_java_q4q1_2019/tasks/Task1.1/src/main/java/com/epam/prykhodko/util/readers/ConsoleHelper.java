package com.epam.prykhodko.util.readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleHelper {

  private static InputStreamReader in = new InputStreamReader(System.in);
  private static BufferedReader bf = new BufferedReader(in);
  private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

  public static String readLine() throws IOException {
    return bf.readLine();
  }

  public static Date readDate() throws IOException, ParseException {
    return formatter.parse(ConsoleHelper.readLine());
  }

  public static int readInt() throws IOException {
    return Integer.parseInt(bf.readLine());
  }

}
