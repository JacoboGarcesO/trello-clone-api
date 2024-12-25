package com.trello.board.application.changecolumn;

import com.trello.shared.application.generic.Request;

public class ChangeColumnRequest extends Request {
  private final String column;
  private final String todoId;

  public ChangeColumnRequest(String aggregateId, String column, String todoId) {
    super(aggregateId);
    this.column = column;
    this.todoId = todoId;
  }

  public String getColumn() {
    return column;
  }

  public String getTodoId() {
    return todoId;
  }
}
