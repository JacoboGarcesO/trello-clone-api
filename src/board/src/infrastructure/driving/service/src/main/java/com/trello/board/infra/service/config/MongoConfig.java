package com.trello.board.infra.service.config;

import com.trello.infra.mongoadapter.adapters.MongoAdapter;
import com.trello.infra.mongoadapter.repositories.IEventsRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.trello.infra.mongoadapter.repositories")
@EntityScan(basePackages = "com.trello.infra.mongoadapter.entities")
public class MongoConfig {
  @Bean
  public MongoAdapter mongoAdapter(IEventsRepository eventsRepository) {
    return new MongoAdapter(eventsRepository);
  }
}
