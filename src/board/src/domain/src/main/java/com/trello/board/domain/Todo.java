package com.trello.board.domain;

import com.trello.board.domain.values.Column;
import com.trello.board.domain.values.Description;
import com.trello.board.domain.values.Name;
import com.trello.board.domain.values.Owner;
import com.trello.board.domain.values.TodoId;
import com.trello.shared.domain.generic.Entity;

import java.util.LinkedList;
import java.util.List;

public class Todo extends Entity<TodoId> {
  private final Name title;
  private final Description description;
  private final Column column;
  private final List<Owner> owners;

  public Todo(TodoId identity, Name title, Description description, Column column) {
    super(identity);
    this.title = title;
    this.description = description;
    this.column = column;
    this.owners = new LinkedList<>();
  }

  public Todo(TodoId identity, Name title, Description description, Column column, List<Owner> owners) {
    super(identity);
    this.title = title;
    this.description = description;
    this.column = column;
    this.owners = owners;
  }

  public Name getTitle() {
    return title;
  }

  public Description getDescription() {
    return description;
  }

  public Column getStatus() {
    return column;
  }

  public List<Owner> getOwners() {
    return owners;
  }

  public void addOwner(Owner owner) {
    if (this.owners.size() >= 2) {
      throw new IllegalStateException("A todo can only have 3 owners");
    }

    if (this.matchOwner(owner)) {
      throw new IllegalStateException("This owner is already added to this todo");
    }

    this.owners.add(owner);
  }

  private boolean matchOwner(Owner owner) {
    return this.owners.stream().anyMatch(o -> o.getValue().containsValue(owner.getValue().get(Owner.Fields.NAME.name())) || o.getValue().containsValue(owner.getValue().get(Owner.Fields.EMAIL.name())));
  }
}
