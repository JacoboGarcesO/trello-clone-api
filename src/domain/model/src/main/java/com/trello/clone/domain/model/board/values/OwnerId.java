package com.trello.clone.domain.model.board.values;

import com.trello.clone.domain.model.generics.Identity;

public class OwnerId extends Identity {
  public OwnerId() {
    super();
  }

  private OwnerId(final String value) {
    super(value);
  }

  public static OwnerId of(final String value) {
    return new OwnerId(value);
  }
}
