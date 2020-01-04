package com.epam.prykhodko.command;

import commandInterface.Command;

public class CommandExit implements Command {

  @Override
  public void execute() {
    System.out.println("Bye,bye,bye!!!");
  }
}
