package com.epam.prykhodko.util;

import static com.epam.prykhodko.constant.Constants.THREAD_INTERRUPTED;

import com.epam.prykhodko.entity.thread.FindThread;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;

public abstract class FindSimpleNumbersUtil {

  private static final Logger LOGGER = Logger.getLogger(FindSimpleNumbersUtil.class);

  private static final List<Integer> list = new ArrayList<>();

  public static void find(int minRange, int maxRange, int countOfThread) {
    long start = System.currentTimeMillis();
    list.clear();
    Thread thread;
    int remain = maxRange / countOfThread;
    int max = remain;
    int min = minRange;
    try {
      for (int i = 0; i < countOfThread; i++) {
        if (i == countOfThread - 1) {
          max += maxRange - max;
          thread = new Thread(new FindThread(min, max));
          thread.join();
          long total = System.currentTimeMillis() - start;
          LOGGER.info(total);
          break;
        }
        new Thread(new FindThread(min, max));
        max += remain;
        min += remain;
      }
    } catch (InterruptedException e) {
      LOGGER.info(THREAD_INTERRUPTED);
    }
  }

  public static void findByExecutor(int minRange, int maxRange, int countOfThread) {
    long start = System.currentTimeMillis();
    list.clear();
    ExecutorService service = Executors.newFixedThreadPool(countOfThread);
    int remain = maxRange / countOfThread;
    int max = remain;
    int min = minRange;
    for (int i = 0; i < countOfThread; i++) {
      if (i == countOfThread - 1) {
        max += maxRange - max;
        service.submit(new FindThread(min, max));
        break;
      }
      service.submit(new FindThread(min, max));
      max += remain;
      min += remain;
    }
    service.shutdown();
    while (true) {
      if (service.isShutdown()) {
        long total = System.currentTimeMillis() - start;
        LOGGER.info(total);
        return;
      }
    }
  }

  public static synchronized void add(int i) {
    list.add(i);
  }

  public static List<Integer> get() {
    return list;
  }

}
