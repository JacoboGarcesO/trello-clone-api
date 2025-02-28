package com.trello.board.domain.events;

import com.trello.board.domain.events.enums.EventsEnum;
import com.trello.shared.domain.generic.DomainEvent;

public class TodoCreated extends DomainEvent {
  private final String id;
  private final String title;
  private final String description;

  public TodoCreated(String id, final String title, final String description) {
    super(EventsEnum.TODO_CREATED.name());
    this.id = id;
    this.title = title;
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getId() {
    return id;
  }
}
