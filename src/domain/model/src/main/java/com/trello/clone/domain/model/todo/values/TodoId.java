package com.trello.clone.domain.model.todo.values;

import com.trello.clone.domain.model.generics.Identity;

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
