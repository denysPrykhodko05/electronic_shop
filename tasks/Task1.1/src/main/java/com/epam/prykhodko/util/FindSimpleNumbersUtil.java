package com.epam.prykhodko.util;

import com.epam.prykhodko.entity.thread.FindThread;
import java.util.ArrayList;
import java.util.List;

public abstract class FindSimpleNumbersUtil {

  private static final List<Integer> list = new ArrayList<>();

  public static void find(int minRange, int maxRange, int countOfThread) {
    int remain = maxRange / countOfThread;
    int max = remain;
    int min = minRange;
    for (int i = 0; i < countOfThread; i++) {
      if (i == countOfThread - 1) {
        max += maxRange - max;
        new FindThread(min, max);
        break;
      }
      new FindThread(min, max);
      max += remain;
      min += remain;
    }
  }

  synchronized public static void add(int i) {
    list.add(i);
  }

  public static List<Integer> get() {
    return list;
  }

}
