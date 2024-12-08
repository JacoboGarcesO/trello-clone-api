package com.trello.clone.domain.model.todo;

import com.trello.clone.domain.model.generics.AggregateRoot;
import com.trello.clone.domain.model.todo.events.OwnerCreated;
import com.trello.clone.domain.model.todo.events.StatusChanged;
import com.trello.clone.domain.model.todo.events.TodoCreated;
import com.trello.clone.domain.model.todo.values.Description;
import com.trello.clone.domain.model.todo.values.Name;
import com.trello.clone.domain.model.todo.values.Status;
import com.trello.clone.domain.model.todo.values.TodoId;

public class Todo extends AggregateRoot<TodoId> {
  protected Name title;
  protected Description description;
  protected Owner owner;
  protected Status status;

  public Todo(TodoId identity) {
    super(identity);
    subscribe(new TodoHandler(this));
  }

  public void changeStatus(final String status) {
    append(new StatusChanged(status)).apply();
  }

  public void createOwner(final String name, final String email) {
    append(new OwnerCreated(name, email)).apply();
  }

  public void createTodo(final String title, final String description) {
    append(new TodoCreated(title, description)).apply();
  }
}
