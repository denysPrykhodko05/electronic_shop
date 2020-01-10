package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;

public class ExitCommand implements Command {

  @Override
  public void execute() {
    System.out.println("Bye,bye,bye!!!");
  }
}
