package com.trello.board.application.shared.ports;

import com.trello.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public interface IEventRepositoryPort {
  Flux<DomainEvent> findByAggregateId(String aggregateId);
  Flux<DomainEvent> findAllAggregates();
  void save(DomainEvent event);
}
