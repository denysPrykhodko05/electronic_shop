package com.epam.prykhodko.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadWrapper {

  private static InputStreamReader in = new InputStreamReader(System.in);
  private static BufferedReader bf = new BufferedReader(in);

  public static String readLine() throws IOException {
    return bf.readLine();
  }

}
