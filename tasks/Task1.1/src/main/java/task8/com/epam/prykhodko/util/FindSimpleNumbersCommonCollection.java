package task8.com.epam.prykhodko.util;

import static com.epam.prykhodko.constant.Constants.THREAD_INTERRUPTED;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import task8.com.epam.prykhodko.entity.FindSimpleNumbersCommonCollectionThread;

public class FindSimpleNumbersCommonCollection {

  private static final Logger LOGGER = Logger.getLogger(FindSimpleNumbersCommonCollection.class);
  private final List<Integer> list;

  public FindSimpleNumbersCommonCollection(List<Integer> list) {
    this.list = Collections.synchronizedList(list);
  }

  public void find(int minRange, int maxRange, int countOfThread) {
    Thread[] thread = new Thread[countOfThread];
    int remain = maxRange / countOfThread;
    int leftBoarder = minRange;
    int rightBoarder = remain;
    for (int i = 0; i < countOfThread; i++) {
      if (i == countOfThread - 1) {
        rightBoarder += maxRange - rightBoarder;
        thread[i] = new Thread(
            new FindSimpleNumbersCommonCollectionThread(list, leftBoarder, rightBoarder));
        thread[i].start();
        break;
      }
      thread[i] = new Thread(
          new FindSimpleNumbersCommonCollectionThread(list, leftBoarder, rightBoarder));
      thread[i].start();
      leftBoarder += remain;
      rightBoarder += remain;
    }

    for (int i = 0; i < countOfThread; i++) {
      try {
        thread[i].join();
      } catch (InterruptedException e) {
        LOGGER.info(THREAD_INTERRUPTED);
      }
    }
  }

  public void findByExecutor(int minRange, int maxRange, int countOfThread) {
    ExecutorService service = Executors.newFixedThreadPool(countOfThread);

    int remain = maxRange / countOfThread;
    int leftBoarder = minRange;
    int rightBoarder = remain;
    for (int i = 0; i < countOfThread; i++) {
      if (i == countOfThread - 1) {
        rightBoarder += maxRange - rightBoarder;
        service.submit(
            new FindSimpleNumbersCommonCollectionThread(list, leftBoarder, rightBoarder));
        break;
      }
      service.submit(
          new FindSimpleNumbersCommonCollectionThread(list, leftBoarder, rightBoarder));
      leftBoarder += remain;
      rightBoarder += remain;
    }

    service.shutdown();
    while (true) {
      if (!service.isTerminated()) {
        continue;
      }
      return;
    }
  }

  public List<Integer> getList() {
    return list;
  }

}
