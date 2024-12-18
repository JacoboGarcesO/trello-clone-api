package com.trello.board.domain.events;

import com.trello.board.domain.events.enums.EventsEnum;
import com.trello.shared.domain.generic.DomainEvent;

public class OwnerAdded extends DomainEvent {
  private final String todoId;
  private final String name;
  private final String email;

  public OwnerAdded(final String name, final String email, String todoId) {
    super(EventsEnum.OWNER_CREATED.name());
    this.todoId = todoId;
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getTodoId() {
    return todoId;
  }
}
