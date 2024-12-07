package com.trello.clone.domain.model.generics;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public abstract class DomainActionsContainer {
  protected Set<Consumer<? super DomainEvent>> actions = new HashSet<>();

  protected void addAction(final Consumer<? super DomainEvent> consumer) {
    actions.add(consumer);
  }
}
