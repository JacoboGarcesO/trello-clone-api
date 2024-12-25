package com.trello.board.application.addtodo;

import com.trello.shared.application.generic.Request;

public class AddTodoRequest extends Request {
  private final String title;
  private final String description;

  public AddTodoRequest(final String aggregateId, final String title, final String description) {
    super(aggregateId);
    this.title = title;
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }
}
