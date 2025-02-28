package com.trello.board.domain.events;

import com.trello.board.domain.events.enums.EventsEnum;
import com.trello.shared.domain.generic.DomainEvent;

public class BoardCreated extends DomainEvent {
  private String name;

  public BoardCreated() {
    super(EventsEnum.BOARD_CREATED.name());
  }

  public BoardCreated(final String name) {
    super(EventsEnum.BOARD_CREATED.name());
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
