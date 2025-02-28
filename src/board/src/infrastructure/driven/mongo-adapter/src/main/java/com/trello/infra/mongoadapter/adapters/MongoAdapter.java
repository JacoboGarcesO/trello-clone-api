package com.trello.infra.mongoadapter.adapters;

import com.trello.board.application.shared.ports.IEventRepositoryPort;
import com.trello.infra.mongoadapter.entities.Event;
import com.trello.infra.mongoadapter.repositories.IEventsRepository;
import com.trello.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public class MongoAdapter implements IEventRepositoryPort {
  private final IEventsRepository eventsRepository;

  public MongoAdapter(IEventsRepository eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Flux<DomainEvent> findByAggregateId(String aggregateId) {
    return this.eventsRepository
      .findAll()
      .filter(event -> event.getDomainEvent().getAggregateRootId().equals(aggregateId))
      .map(Event::getDomainEvent);
  }

  @Override
  public Flux<DomainEvent> findAllAggregates() {
    return this.eventsRepository
      .findAll()
      .map(Event::getDomainEvent);
  }

  @Override
  public void save(DomainEvent event) {
    this.eventsRepository
      .save(new Event(event))
      .subscribe();
  }
}
