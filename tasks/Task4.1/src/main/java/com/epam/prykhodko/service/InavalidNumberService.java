package com.epam.prykhodko.service;

import com.epam.prykhodko.commandInterface.Command;

public class InavalidNumberService implements Command {

  @Override
  public void execute() {
    System.out.println("Invalid input. Try again!!!");
  }
}
