package com.trello.clone.domain.model.board;

import com.trello.clone.domain.model.board.events.*;
import com.trello.clone.domain.model.board.values.*;
import com.trello.clone.domain.model.generics.DomainActionsContainer;
import com.trello.clone.domain.model.generics.DomainEvent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Consumer;

public class BoardHandler extends DomainActionsContainer {
  public BoardHandler(Board board) {
    add(createBoard(board));
    add(addColumn(board));
    add(addTodo(board));
    add(addOwner(board));
    add(changeStatus(board));
  }

  private Consumer<? extends DomainEvent> createBoard(final Board board) {
    return (BoardCreated event) -> {
      board.setName(Name.of(event.getName()));
      board.setColumns(new HashMap<>());
      board.setTodos(new LinkedList<>());
    };
  }

  private Consumer<? extends DomainEvent> addColumn(final Board board) {
    return (ColumnAdded event) -> {
      if (event.getIndex() < 0) {
        throw new IllegalStateException("Column index must be greater than 0");
      }

      if (board.getColumns().containsKey(event.getIndex())) {
        throw new IllegalStateException("This column already exists");
      }

      if (board.getColumns().get(event.getIndex()) != null) {
        throw new IllegalStateException("This column already exists");
      }

      board.getColumns().put(event.getIndex(), Status.of(event.getName()));
    };
  }

  private Consumer<? extends DomainEvent> addTodo(final Board board) {
    return (TodoCreated event) -> {
      Todo todo = new Todo(
        new TodoId(),
        Name.of(event.getTitle()),
        Description.of(event.getDescription()),
        Status.of(board.getColumns().get(0).getValue())
      );

      board.getTodos().add(todo);
    };
  }

  private Consumer<? extends DomainEvent> addOwner(final Board board) {
    return (OwnerAdded event) -> {
      Optional<Todo> todo = board.getTodos().stream().filter(t -> t.getIdentity().equals(TodoId.of(event.getTodoId()))).findFirst();

      if (todo.isEmpty()) {
        throw new IllegalStateException("This todo does not exist");
      }

      board.getTodos().remove(todo.get());
      Todo newTodo = new Todo(todo.get().getIdentity(), todo.get().getTitle(), todo.get().getDescription(), todo.get().getStatus());
      newTodo.addOwner(new Owner(new OwnerId(), Name.of(event.getName()), Email.of(event.getEmail())));
      board.getTodos().add(newTodo);
    };
  }

  private Consumer<? extends DomainEvent> changeStatus(final Board board) {
    return (StatusChanged event) -> {
      Optional<Todo> todo = board.getTodos().stream().filter(t -> t.getIdentity().equals(TodoId.of(event.getTodoId()))).findFirst();

      if (todo.isEmpty()) {
        throw new IllegalStateException("This todo does not exist");
      }

      board.getTodos().remove(todo.get());
      Todo newTodo = new Todo(todo.get().getIdentity(), todo.get().getTitle(), todo.get().getDescription(), Status.of(event.getStatus()));
      board.getTodos().add(newTodo);
    };
  }
}
