package com.trello.clone.domain.model.board;

import com.trello.clone.domain.model.board.values.Description;
import com.trello.clone.domain.model.board.values.Name;
import com.trello.clone.domain.model.board.values.Owner;
import com.trello.clone.domain.model.board.values.Status;
import com.trello.clone.domain.model.board.values.TodoId;
import com.trello.clone.domain.model.generics.Entity;

import java.util.LinkedList;
import java.util.List;

public class Todo extends Entity<TodoId> {
  private final Name title;
  private final Description description;
  private final Status status;
  private final List<Owner> owners;

  public Todo(TodoId identity, Name title, Description description, Status status) {
    super(identity);
    this.title = title;
    this.description = description;
    this.status = status;
    this.owners = new LinkedList<>();
  }

  public Todo(TodoId identity, Name title, Description description, Status status, List<Owner> owners) {
    super(identity);
    this.title = title;
    this.description = description;
    this.status = status;
    this.owners = owners;
  }

  public Name getTitle() {
    return title;
  }

  public Description getDescription() {
    return description;
  }

  public Status getStatus() {
    return status;
  }

  public List<Owner> getOwners() {
    return owners;
  }

  public void addOwner(Owner owner) {
    if (this.owners.size() > 2) {
      throw new IllegalStateException("A todo can only have 3 owners");
    }

    if (this.matchOwner(owner)) {
      throw new IllegalStateException("This owner is already added to this todo");
    }

    this.owners.add(owner);
  }

  private boolean matchOwner(Owner owner) {
    return this.owners.stream().anyMatch(o ->
      o.getValue().containsValue(owner.getValue().get(Owner.Fields.NAME.name()))
        || o.getValue().containsValue(owner.getValue().get(Owner.Fields.EMAIL.name()))
    );
  }
}
