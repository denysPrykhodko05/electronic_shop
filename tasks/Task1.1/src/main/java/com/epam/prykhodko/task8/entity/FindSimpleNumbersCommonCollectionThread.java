package com.epam.prykhodko.task8.entity;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import java.util.List;

public class FindSimpleNumbersCommonCollectionThread implements Runnable {

  private int leftBoarder;
  private int rightBoarder;
  private int amountOfThreads;
  private List<Integer> list;

  public FindSimpleNumbersCommonCollectionThread(List<Integer> list, int leftBoarder, int rightBoarder,
      int amountOfThreads) {
    this.list = list;
    this.leftBoarder = leftBoarder;
    this.rightBoarder = rightBoarder;
    this.amountOfThreads = amountOfThreads;
  }

  private boolean isPrime(int number) {
    if (number < 2){
      return true;
    }
    for (int j = 2; j <= number / 2; j++) {
      if ((number % j) == INTEGER_ZERO) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void run() {
    for (int i = leftBoarder; i <= rightBoarder; i += amountOfThreads) {
      if (isPrime(i)) {
        list.add(i);
      }
    }
  }
}
