package com.epam.prykhodko.command;

import commandInterface.Command;

public class CommandInavalidNumber implements Command {

  @Override
  public void execute() {
    System.out.println("Invalid input. Try again!!!");
  }
}
