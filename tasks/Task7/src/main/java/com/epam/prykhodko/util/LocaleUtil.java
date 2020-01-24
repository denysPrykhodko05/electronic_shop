package com.epam.prykhodko.util;

import static com.epam.prykhodko.constants.Constants.INCORRECT_INPUT;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class LocaleUtil {

  private static final Map<String, Locale> localeMap = new HashMap<>();

  private static void mapInit() {
    localeMap.put("1", new Locale("ru"));
    localeMap.put("2", new Locale("en"));
  }

  public static Locale getLocale() {
    mapInit();
    String choose;
    System.out.println("What language do you want to use?\n1-Russian\n2-English");
    Locale locale = null;
    while (Objects.isNull(locale)) {
      try {
        choose = ConsoleHelper.readLine();
        locale = localeMap.get(choose);
      } catch (IOException e) {
        System.out.println(INCORRECT_INPUT);
      }
    }
    return locale;
  }

}
