package com.trello.board.application.addowner;

import com.trello.shared.application.generic.Request;

public class AddOwnerRequest extends Request {
  private final String name;
  private final String email;
  private final String todoId;

  public AddOwnerRequest(final String aggregateId, final String name, final String email, final String todoId) {
    super(aggregateId);
    this.name = name;
    this.email = email;
    this.todoId = todoId;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getTodoId() {
    return todoId;
  }
}
