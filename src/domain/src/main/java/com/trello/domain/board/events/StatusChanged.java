package com.trello.domain.board.events;

import com.trello.domain.board.events.enums.EventsEnum;
import com.trello.domain.generics.DomainEvent;

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
