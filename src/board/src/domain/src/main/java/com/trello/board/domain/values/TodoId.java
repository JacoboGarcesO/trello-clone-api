package com.trello.board.domain.values;

import com.trello.shared.domain.generic.Identity;

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
