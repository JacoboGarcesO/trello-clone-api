package com.trello.clone.domain.model.todo.events;

import com.trello.clone.domain.model.generics.DomainEvent;
import com.trello.clone.domain.model.todo.events.enums.EventsEnum;

public class StatusChanged extends DomainEvent {
  private final String status;

  public StatusChanged(final String status) {
    super(EventsEnum.STATUS_CHANGED.name());
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
