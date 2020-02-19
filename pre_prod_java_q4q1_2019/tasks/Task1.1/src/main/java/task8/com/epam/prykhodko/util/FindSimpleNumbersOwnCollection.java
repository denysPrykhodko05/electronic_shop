package task8.com.epam.prykhodko.util;

import static com.epam.prykhodko.constant.Constants.THREAD_INTERRUPTED;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.log4j.Logger;
import task8.com.epam.prykhodko.entity.FindSimpleNumbersOwnCollectionThread;

public class FindSimpleNumbersOwnCollection {

  private static final Logger LOGGER = Logger.getLogger(FindSimpleNumbersCommonCollection.class);
  private final List<Integer> list = Collections.synchronizedList(new ArrayList<>());
  private List<Future<List<Integer>>> futureList = new ArrayList<>();

  public void find(int minRange, int maxRange, int countOfThread) {
    List<FindSimpleNumbersOwnCollectionThread> listThread = new ArrayList<>();
    int remain = maxRange / countOfThread;
    int leftBoarder = minRange;
    int rightBorder = remain;
    for (int i = 0; i < countOfThread; i++) {
      if (i == countOfThread - 1) {
        rightBorder += maxRange - rightBorder;
        listThread.add(new FindSimpleNumbersOwnCollectionThread(leftBoarder, rightBorder));
        break;
      }
      listThread
          .add(new FindSimpleNumbersOwnCollectionThread(leftBoarder, rightBorder));
      rightBorder += remain;
      leftBoarder += remain;
    }

    listThread.forEach(FindSimpleNumbersOwnCollectionThread::run);

    for (FindSimpleNumbersOwnCollectionThread findSimpleNumbersOwnCollectionThread : listThread) {
      try {
        findSimpleNumbersOwnCollectionThread.join();
      } catch (InterruptedException e) {
        LOGGER.info(THREAD_INTERRUPTED);
      }
    }

    listThread.forEach(e -> list.addAll(e.getResult()));
  }

  public void findByExecutor(int minRange, int maxRange, int countOfThread) {
    ExecutorService service = Executors.newFixedThreadPool(countOfThread);
    int remain = maxRange / countOfThread;
    int leftBoarder = minRange;
    int rightBoarder = remain;
    for (int i = 0; i < countOfThread; i++) {
      if (i == countOfThread - 1) {
        rightBoarder += maxRange - rightBoarder;
        futureList.add(service.submit(
            (Callable<List<Integer>>) new FindSimpleNumbersOwnCollectionThread(leftBoarder,
                rightBoarder)));
        break;
      }
      futureList.add(service.submit(
          (Callable<List<Integer>>) new FindSimpleNumbersOwnCollectionThread(leftBoarder,
              rightBoarder)));
      rightBoarder += remain;
      leftBoarder += remain;
    }

    while (!futureList.isEmpty()) {
      try {
        for (int i = futureList.size() - 1; i >= 0; i--) {
          Future<List<Integer>> future = futureList.get(i);
          if (future.isDone()) {
            list.addAll(future.get());
            futureList.remove(future);
          }
        }
      } catch (InterruptedException | ExecutionException ex) {
        LOGGER.info(THREAD_INTERRUPTED);
      }
    }
    service.shutdown();
  }

  public List<Integer> getList() {
    return list;
  }

}
