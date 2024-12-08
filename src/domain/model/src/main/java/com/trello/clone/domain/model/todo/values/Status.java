package com.trello.clone.domain.model.todo.values;

import com.trello.clone.domain.model.generics.IValueObject;
import com.trello.clone.domain.model.todo.constants.TodoStatus;

public class Status implements IValueObject<TodoStatus> {
  private final TodoStatus value;

  private Status(final String value) {
    this.value = TodoStatus.of(value);
  }

  public static Status of(final String value) {
    return new Status(value);
  }

  @Override
  public TodoStatus getValue() {
    return value;
  }
}
