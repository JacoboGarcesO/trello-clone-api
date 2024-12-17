package com.trello.domain.board.values;

import com.trello.domain.generics.Identity;

public class TodoId extends Identity {
  public TodoId() {
    super();
  }

  private TodoId(final String value) {
    super(value);
  }

  public static TodoId of(final String value) {
    return new TodoId(value);
  }
}
