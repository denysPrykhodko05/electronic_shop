package com.epam.prykhodko.entity.thread;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;

import com.epam.prykhodko.util.FindSimpleNumbersUtil;

public class FindThread extends Thread {

  private static final Object monitor = new Object();

  private int minRange;
  private int maxRange;

  public FindThread(int minRange, int maxRange) {
    this.minRange = minRange;
    this.maxRange = maxRange;
    start();
  }

  private void find() {
    boolean isPrime;
    for (int i = minRange; i < maxRange; i++) {
      isPrime = true;
      for (int j = 2; j <= i / j; j++) {
        if ((i % j) == INTEGER_ZERO) {
          isPrime = false;
          break;
        }
      }
      if (isPrime) {
        synchronized (monitor) {
          FindSimpleNumbersUtil.add(i);
        }
      }
    }
  }

  @Override
  public void run() {
    find();
    super.run();
  }
}
