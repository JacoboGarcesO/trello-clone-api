package com.trello.clone.domain.model.generics;

import java.util.Optional;

public abstract class Command<I extends Identity> {
  protected final Optional<I> identity;

  protected Command(final I identity) {
    this.identity = Optional.ofNullable(identity);
  }
}
