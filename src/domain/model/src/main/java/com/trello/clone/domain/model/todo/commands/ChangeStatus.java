package com.trello.clone.domain.model.todo.commands;

import com.trello.clone.domain.model.generics.Command;
import com.trello.clone.domain.model.todo.values.TodoId;

public class ChangeStatus extends Command<TodoId> {
  private final String status;

  public ChangeStatus(TodoId identity, String status) {
    super(identity);
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
