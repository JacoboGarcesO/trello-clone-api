package com.trello.board.domain.events;

import com.trello.board.domain.events.enums.EventsEnum;
import com.trello.shared.domain.generic.DomainEvent;

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
