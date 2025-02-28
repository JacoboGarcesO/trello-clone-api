package com.trello.infra.mongoadapter.repositories;

import com.trello.infra.mongoadapter.entities.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IEventsRepository extends ReactiveMongoRepository<Event, String> {
}
