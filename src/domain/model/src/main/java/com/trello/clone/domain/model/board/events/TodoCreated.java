package com.trello.clone.domain.model.board.events;

import com.trello.clone.domain.model.generics.DomainEvent;
import com.trello.clone.domain.model.board.events.enums.EventsEnum;

public class TodoCreated extends DomainEvent {
  private final String title;
  private final String description;

  public TodoCreated(final String title, final String description) {
    super(EventsEnum.TODO_CREATED.name());
    this.title = title;
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }
}
