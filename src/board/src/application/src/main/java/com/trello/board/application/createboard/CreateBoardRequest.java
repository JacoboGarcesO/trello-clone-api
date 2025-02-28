package com.trello.board.application.createboard;

import com.trello.shared.application.generic.Request;

public class CreateBoardRequest extends Request {
  private String name;

  public CreateBoardRequest() {
    super();
  }

  public CreateBoardRequest(String name) {
    super(null);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
