package com.epam.prykhodko.entity.thread;

import com.epam.prykhodko.util.FindSequence;

public class FindSequenceThread implements Runnable {

  private final FindSequence findSequence;

  public FindSequenceThread(FindSequence findSequence) {
    this.findSequence = findSequence;
  }

  @Override
  public void run() {
    findSequence.findSequence();
  }
}
