package com.trello.domain.board.events;

import com.trello.domain.board.events.enums.EventsEnum;
import com.trello.domain.generics.DomainEvent;

public class BoardCreated extends DomainEvent {
  private final String name;

  public BoardCreated(final String name) {
    super(EventsEnum.BOARD_CREATED.name());
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
