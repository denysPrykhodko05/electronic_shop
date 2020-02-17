package com.epam.prykhodko.command.impl;

import com.epam.prykhodko.command.Command;
import com.epam.prykhodko.service.CacheService;

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
