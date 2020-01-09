package com.epam.prykhodko.command;

import com.epam.prykhodko.commandInterface.Command;
import com.epam.prykhodko.service.impl.CacheService;

public class GetLastFiveProductsCommand implements Command {

  private final CacheService cacheRepository;

  public GetLastFiveProductsCommand(CacheService cacheRepository) {
    this.cacheRepository = cacheRepository;
  }

  @Override
  public void execute() {
    cacheRepository.get().forEach(System.out::println);
  }
}
