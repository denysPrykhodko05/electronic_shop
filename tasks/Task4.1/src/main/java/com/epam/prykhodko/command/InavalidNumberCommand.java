package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;

public class InavalidNumberCommand implements Command {

  @Override
  public void execute() {
    System.out.println("Invalid input. Try again!!!");
  }
}
