package com.epam.prykhodko.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ConsoleScanner {

  public static final Scanner scanner = new Scanner(System.in);
  private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

  public static String readLine(){
    return scanner.nextLine();
  }

  public static Date readDate() throws ParseException {
    return formatter.parse(scanner.nextLine());
  }

  public static int readInt() {
    return Integer.parseInt(scanner.nextLine());
  }
}
