package com.trello.clone.domain.model.todo.constants;

public enum TodoStatus {
  PENDING, IN_PROGRESS, COMPLETED;

  public static TodoStatus of(final String value) {
    return valueOf(value.toUpperCase());
  }
}
