package com.trello.board.application.shared.board;

import java.util.List;
import java.util.Map;

public class BoardResponse {
  private final String boardId;
  private final String name;
  private final Map<Integer, String> columns;
  private final List<TodoResponse> todos;

  public BoardResponse(String boardId, String name, Map<Integer, String> columns, List<TodoResponse> todos) {
    this.boardId = boardId;
    this.name = name;
    this.columns = columns;
    this.todos = todos;
  }

  public String getBoardId() {
    return boardId;
  }

  public String getName() {
    return name;
  }

  public Map<Integer, String> getColumns() {
    return columns;
  }

  public List<TodoResponse> getTodos() {
    return todos;
  }
}
