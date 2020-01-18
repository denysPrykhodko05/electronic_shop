package com.epam.prykhodko.command.impl;

import com.epam.prykhodko.command.Command;

public class ExitCommand implements Command {

  @Override
  public void execute() {
    System.out.println("Bye,bye,bye!!!");
  }
}
