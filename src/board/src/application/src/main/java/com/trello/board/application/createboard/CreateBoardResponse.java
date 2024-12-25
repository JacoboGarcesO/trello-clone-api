package com.trello.board.application.createboard;

public class CreateBoardResponse {
  private final String boardId;
  private final String name;

  public CreateBoardResponse(String boardId, String name) {
    this.boardId = boardId;
    this.name = name;
  }

  public String getBoardId() {
    return boardId;
  }

  public String getName() {
    return name;
  }
}
