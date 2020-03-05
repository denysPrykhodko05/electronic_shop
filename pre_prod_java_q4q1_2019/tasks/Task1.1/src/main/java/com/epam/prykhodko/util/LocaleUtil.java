package com.epam.prykhodko.util;

import static com.epam.prykhodko.constant.Constants.INCORRECT_INPUT;

import com.epam.prykhodko.util.readers.ConsoleHelper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.apache.log4j.Logger;

public abstract class LocaleUtil {

  private static final Logger LOGGER = Logger.getLogger(LocaleUtil.class);
  private static final Map<Integer, Locale> localeMap = new HashMap<>();

  private static void mapInit() {
    localeMap.put(1, new Locale("ru"));
    localeMap.put(2, new Locale("en"));
  }

  public static Locale getLocale() {
    mapInit();
    int choose;
    boolean input = false;
    LOGGER.info("What language do you want to use?\n1-Russian\n2-English");
    Locale locale = null;
    while (!input) {
      try {
        choose = ConsoleHelper.readInt();
        locale = localeMap.get(choose);
        input = true;
        if (Objects.isNull(locale)) {
          input = false;
        }
      } catch (IOException | NumberFormatException e) {
        LOGGER.error(INCORRECT_INPUT);
        input = false;
      }
    }
    return locale;
  }

}
