package com.trello.board.domain.values;


import com.trello.shared.domain.generic.Identity;

public class BoardId extends Identity {
  public BoardId() {
    super();
  }

  private BoardId(final String value) {
    super(value);
  }

  public static BoardId of(final String value) {
    return new BoardId(value);
  }
}
