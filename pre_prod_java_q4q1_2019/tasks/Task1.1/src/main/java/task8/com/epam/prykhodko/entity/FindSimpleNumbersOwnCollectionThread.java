package task8.com.epam.prykhodko.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import task8.com.epam.prykhodko.util.FindPrimeNumbersUtil;

public class FindSimpleNumbersOwnCollectionThread extends Thread implements
    Callable<List<Integer>> {

  private int leftBoarder;
  private int rightBoarder;
  private List<Integer> list = new ArrayList<>();

  public FindSimpleNumbersOwnCollectionThread(int leftBoarder, int rightBoarder) {
    this.leftBoarder = leftBoarder;
    this.rightBoarder = rightBoarder;
  }

  public List<Integer> getResult() {
    return list;
  }

  @Override
  public void run() {
    FindPrimeNumbersUtil.search(leftBoarder, rightBoarder, list);
  }

  @Override
  public List<Integer> call() {
    run();
    return list;
  }
}
