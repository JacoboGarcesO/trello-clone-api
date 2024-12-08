package com.trello.clone.domain.model.todo.events;

import com.trello.clone.domain.model.generics.DomainEvent;
import com.trello.clone.domain.model.todo.events.enums.EventsEnum;

public class OwnerCreated extends DomainEvent {
  private final String name;
  private final String email;

  public OwnerCreated(final String name, final String email) {
    super(EventsEnum.OWNER_CREATED.name());
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }
}
