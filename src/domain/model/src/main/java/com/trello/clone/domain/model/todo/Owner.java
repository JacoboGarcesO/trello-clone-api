package com.trello.clone.domain.model.todo;

import com.trello.clone.domain.model.generics.Entity;
import com.trello.clone.domain.model.todo.values.Email;
import com.trello.clone.domain.model.todo.values.Name;
import com.trello.clone.domain.model.todo.values.OwnerId;

public class Owner extends Entity<OwnerId> {
  private Name name;
  private Email email;

  protected Owner(OwnerId identity) {
    super(identity);
  }

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
