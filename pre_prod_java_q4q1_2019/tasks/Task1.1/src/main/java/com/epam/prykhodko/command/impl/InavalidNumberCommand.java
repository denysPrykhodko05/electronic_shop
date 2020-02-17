package com.epam.prykhodko.command.impl;

import com.epam.prykhodko.command.Command;

public class InavalidNumberCommand implements Command {

  @Override
  public void execute() {
    System.out.println("Invalid input. Try again!!!");
  }
}
