package com.epam.prykhodko.task8.util;

import static com.epam.prykhodko.constant.Constants.THREAD_INTERRUPTED;

import com.epam.prykhodko.task8.entity.FindSimpleNumbersCommonCollectionThread;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;

public class FindSimpleNumbersCommonCollection {

  private static final Logger LOGGER = Logger.getLogger(FindSimpleNumbersCommonCollection.class);
  private final List<Integer> list;

  public FindSimpleNumbersCommonCollection(List<Integer> list) {
    this.list = list;
  }

  public void find(int minRange, int maxRange, int countOfThread) {
    Thread[] thread = new Thread[countOfThread];

    for (int i = 0; i < countOfThread; i++) {
      thread[i] = new Thread(new FindSimpleNumbersCommonCollectionThread(list,minRange+i,maxRange,countOfThread));
    }

    for (int i = 0; i < countOfThread; i++) {
      thread[i].start();
    }

    for (int i = 0; i < countOfThread; i++) {
      try {
        thread[i].join();
      } catch (InterruptedException e) {
        LOGGER.info(THREAD_INTERRUPTED);
      }
    }
    list.forEach(LOGGER::info);
  }

  public void findByExecutor(int minRange, int maxRange, int countOfThread) {
    ExecutorService service = Executors.newFixedThreadPool(countOfThread);

    for (int i = 0; i < countOfThread; i++) {
       service.submit(new FindSimpleNumbersCommonCollectionThread(list,minRange+i,maxRange,countOfThread));
    }

    service.shutdown();
    while (true){
      if (!service.isTerminated()){
        continue;
      }
      list.forEach(LOGGER::info);
      return;
    }
  }
}
