package com.epam.prykhodko.entity.thread;

import com.epam.prykhodko.util.FindSequenceUtil;

public class FindSequenceThread implements Runnable {

  private final FindSequenceUtil findSequenceUtil;

  public FindSequenceThread(FindSequenceUtil findSequenceUtil) {
    this.findSequenceUtil = findSequenceUtil;
  }

  @Override
  public void run() {
    findSequenceUtil.findSequence();
  }
}
