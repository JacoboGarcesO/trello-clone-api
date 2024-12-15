package com.trello.clone.domain.model.board;

import com.trello.clone.domain.model.board.events.BoardCreated;
import com.trello.clone.domain.model.board.events.ColumnAdded;
import com.trello.clone.domain.model.board.events.OwnerAdded;
import com.trello.clone.domain.model.board.events.StatusChanged;
import com.trello.clone.domain.model.board.events.TodoCreated;
import com.trello.clone.domain.model.board.values.Description;
import com.trello.clone.domain.model.board.values.Name;
import com.trello.clone.domain.model.board.values.Owner;
import com.trello.clone.domain.model.board.values.Column;
import com.trello.clone.domain.model.board.values.TodoId;
import com.trello.clone.domain.model.generics.DomainActionsContainer;
import com.trello.clone.domain.model.generics.DomainEvent;

import java.util.HashMap;
import java.util.LinkedList;
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
      board.validateIndex(event.getIndex());
      board.validateColumDoesNotMatch(event.getName());
      board.getColumns().put(event.getIndex(), Column.of(event.getName()));
    };
  }

  private Consumer<? extends DomainEvent> addTodo(final Board board) {
    return (TodoCreated event) -> {
      board.validateColumnsExist();
      board.validateTodoDuplicated(event.getTitle());

      final Todo todo = new Todo(
        new TodoId(),
        Name.of(event.getTitle()),
        Description.of(event.getDescription()),
        Column.of(board.getColumns().get(0).getValue())
      );

      board.getTodos().add(todo);
    };
  }

  private Consumer<? extends DomainEvent> addOwner(final Board board) {
    return (OwnerAdded event) -> {
      final Todo todo = board.getTodo(event.getTodoId());
      board.getTodos().removeIf(t -> t.getIdentity().equals(todo.getIdentity()));
      final Todo newTodo = new Todo(todo.getIdentity(), todo.getTitle(), todo.getDescription(), todo.getStatus(), todo.getOwners());
      newTodo.addOwner(Owner.of(event.getName(), event.getEmail()));
      board.getTodos().add(newTodo);
    };
  }

  private Consumer<? extends DomainEvent> changeStatus(final Board board) {
    return (StatusChanged event) -> {
      board.validateColumMatches(event.getStatus());
      final Todo todo = board.getTodo(event.getTodoId());
      board.getTodos().removeIf(t -> t.getIdentity().equals(todo.getIdentity()));
      final Todo newTodo = new Todo(todo.getIdentity(), todo.getTitle(), todo.getDescription(), Column.of(event.getStatus()));
      board.getTodos().add(newTodo);
    };
  }
}
