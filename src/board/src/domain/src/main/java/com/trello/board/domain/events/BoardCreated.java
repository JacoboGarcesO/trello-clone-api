package com.trello.board.domain.events;

import com.trello.board.domain.events.enums.EventsEnum;
import com.trello.shared.domain.generic.DomainEvent;

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
