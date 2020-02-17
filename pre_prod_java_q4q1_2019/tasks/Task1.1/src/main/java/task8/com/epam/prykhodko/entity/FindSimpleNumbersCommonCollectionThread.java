package task8.com.epam.prykhodko.entity;

import java.util.List;
import task8.com.epam.prykhodko.util.FindPrimeNumbersUtil;

public class FindSimpleNumbersCommonCollectionThread implements Runnable {

  private int leftBoarder;
  private int rightBoarder;
  private List<Integer> list;

  public FindSimpleNumbersCommonCollectionThread(List<Integer> list, int leftBoarder,
      int rightBoarder) {
    this.list = list;
    this.leftBoarder = leftBoarder;
    this.rightBoarder = rightBoarder;
  }

  @Override
  public void run() {
    FindPrimeNumbersUtil.search(leftBoarder, rightBoarder, list);
  }
}
