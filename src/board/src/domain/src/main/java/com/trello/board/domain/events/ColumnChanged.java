package com.trello.board.domain.events;

import com.trello.board.domain.events.enums.EventsEnum;
import com.trello.shared.domain.generic.DomainEvent;

public class ColumnChanged extends DomainEvent {
  private final String column;
  private final String todoId;

  public ColumnChanged(final String status, String todoId) {
    super(EventsEnum.STATUS_CHANGED.name());
    this.column = status;
    this.todoId = todoId;
  }

  public String getColumn() {
    return column;
  }

  public String getTodoId() {
    return todoId;
  }
}
