package com.trello.clone.domain.model.generics;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public abstract class DomainActionsContainer {
  protected Set<Consumer<? super DomainEvent>> actions = new HashSet<>();

  protected void add(final Consumer<? extends DomainEvent> consumer) {
    actions.add((Consumer<? super DomainEvent>)consumer);
  }
}
