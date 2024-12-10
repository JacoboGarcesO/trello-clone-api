package com.trello.clone.domain.model.board.commands;

import com.trello.clone.domain.model.generics.Command;
import com.trello.clone.domain.model.board.values.TodoId;

public class CreateOwner extends Command<TodoId> {
  private final String name;
  private final String email;

  public CreateOwner(TodoId identity, String name, String email) {
    super(identity);
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }
}
