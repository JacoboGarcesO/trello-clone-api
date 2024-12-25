package com.trello.board.application.createboard;

import com.trello.shared.application.generic.Request;

public class CreateBoardRequest extends Request {
  private final String name;

  public CreateBoardRequest(String name) {
    super(null);
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
