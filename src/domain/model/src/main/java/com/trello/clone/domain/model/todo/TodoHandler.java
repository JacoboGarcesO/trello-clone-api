package com.trello.clone.domain.model.todo;

import com.trello.clone.domain.model.generics.DomainActionsContainer;
import com.trello.clone.domain.model.todo.constants.TodoStatus;
import com.trello.clone.domain.model.todo.events.OwnerCreated;
import com.trello.clone.domain.model.todo.events.StatusChanged;
import com.trello.clone.domain.model.todo.events.TodoCreated;
import com.trello.clone.domain.model.todo.values.*;

public class TodoHandler extends DomainActionsContainer {
  public TodoHandler(Todo todo) {
    add((OwnerCreated event) -> {
      todo.owner = new Owner(new OwnerId(), Name.of(event.getName()), Email.of(event.getEmail()));
    });

    add((TodoCreated event) -> {
      todo.title = Name.of(event.getTitle());
      todo.description = Description.of(event.getDescription());
      todo.status = Status.of(TodoStatus.PENDING.name());
    });

    add((StatusChanged event) -> {
      todo.status = Status.of(event.getStatus());
    });
  }
}
