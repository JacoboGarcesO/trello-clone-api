package com.trello.domain.generics;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public class DomainActionsHandler {
  private final List<DomainEvent> events = new LinkedList<>();
  private final Map<String, AtomicLong> versions = new ConcurrentHashMap<>();
  private final Set<Consumer<? super DomainEvent>> actions = new HashSet<>();

  public List<DomainEvent> getEvents() {
    return events;
  }

  public final void subscribe(final DomainActionsContainer container) {
    actions.addAll(container.actions);
  }

  public final IApplyEvent append(final DomainEvent event) {
    events.add(event);
    return () -> apply(event);
  }

  private void apply(final DomainEvent event) {
    actions.forEach(action -> handle(event, action));
  }

  private void handle(final DomainEvent event, final Consumer<? super DomainEvent> action) {
    try {
      action.accept(event);
      long newVersion = increaseVersion(event);
      event.setVersion(newVersion);
    } catch (ClassCastException ignored) { }
  }

  private long increaseVersion(final DomainEvent event) {
    final AtomicLong version = versions.get(event.getType());
    final long newVersion = version == null ? event.getVersion() : version.incrementAndGet();
    versions.put(event.getType(), new AtomicLong(newVersion));
    return newVersion;
  }
}
