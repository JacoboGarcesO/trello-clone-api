package com.trello.clone.domain.model.board;

import com.trello.clone.domain.model.generics.Entity;
import com.trello.clone.domain.model.board.values.Email;
import com.trello.clone.domain.model.board.values.Name;
import com.trello.clone.domain.model.board.values.OwnerId;

public class Owner extends Entity<OwnerId> {
  private final Name name;
  private final Email email;

  protected Owner(OwnerId identity, Name name, Email email) {
    super(identity);
    this.name = name;
    this.email = email;
  }

  public Name getName() {
    return name;
  }

  public Email getEmail() {
    return email;
  }
}
