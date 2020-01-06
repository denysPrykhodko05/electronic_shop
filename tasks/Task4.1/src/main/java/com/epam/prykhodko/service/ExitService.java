package com.epam.prykhodko.service;

import com.epam.prykhodko.commandInterface.Command;

public class ExitService implements Command {

  @Override
  public void execute() {
    System.out.println("Bye,bye,bye!!!");
  }
}
