package com.trello.board.domain.events;

import com.trello.board.domain.events.enums.EventsEnum;
import com.trello.shared.domain.generic.DomainEvent;

public class ColumnAdded extends DomainEvent {
  private final String name;
  private final Integer index;

  public ColumnAdded(String name, Integer index) {
    super(EventsEnum.COLUMN_ADDED.name());
    this.name = name;
    this.index = index;
  }

  public String getName() {
    return name;
  }

  public Integer getIndex() {
    return index;
  }
}
