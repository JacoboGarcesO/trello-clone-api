package com.trello.board.application.shared.repositories;

import com.trello.shared.domain.generic.DomainEvent;

import java.util.List;

public interface IEventRepository {
  List<DomainEvent> findByAggregateId(String aggregateId);
  void save(DomainEvent event);
}
