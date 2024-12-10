package com.trello.clone.domain.model.board.events;

import com.trello.clone.domain.model.generics.DomainEvent;
import com.trello.clone.domain.model.board.events.enums.EventsEnum;

public class StatusChanged extends DomainEvent {
  private final String status;
  private final String todoId;

  public StatusChanged(final String status, String todoId) {
    super(EventsEnum.STATUS_CHANGED.name());
    this.status = status;
    this.todoId = todoId;
  }

  public String getStatus() {
    return status;
  }

  public String getTodoId() {
    return todoId;
  }
}
