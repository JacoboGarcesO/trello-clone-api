package com.trello.clone.domain.model.board.values;

import com.trello.clone.domain.model.generics.Identity;

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
