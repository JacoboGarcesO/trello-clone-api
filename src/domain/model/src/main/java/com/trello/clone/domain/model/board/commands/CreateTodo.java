package com.trello.clone.domain.model.board.commands;

import com.trello.clone.domain.model.generics.Command;
import com.trello.clone.domain.model.board.values.TodoId;

public class CreateTodo extends Command<TodoId> {
  private final String title;
  private final String description;

  public CreateTodo(TodoId identity, String title, String description) {
    super(identity);
    this.title = title;
    this.description = description;
  }
}
