package com.trello.board.domain;

import com.trello.board.domain.events.BoardCreated;
import com.trello.board.domain.events.ColumnAdded;
import com.trello.board.domain.events.OwnerAdded;
import com.trello.board.domain.events.ColumnChanged;
import com.trello.board.domain.events.TodoCreated;
import com.trello.board.domain.values.Column;
import com.trello.board.domain.values.Description;
import com.trello.board.domain.values.Name;
import com.trello.board.domain.values.Owner;
import com.trello.board.domain.values.TodoId;
import com.trello.shared.domain.generic.DomainActionsContainer;
import com.trello.shared.domain.generic.DomainEvent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Consumer;

public class BoardHandler extends DomainActionsContainer {
  public BoardHandler(Board board) {
    add(createBoard(board));
    add(addColumn(board));
    add(addTodo(board));
    add(addOwner(board));
    add(changeColumn(board));
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

      final Todo todo = new Todo(TodoId.of(event.getId()), Name.of(event.getTitle()), Description.of(event.getDescription()), Column.of(board.getColumns().get(0).getValue()));

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

  private Consumer<? extends DomainEvent> changeColumn(final Board board) {
    return (ColumnChanged event) -> {
      board.validateColumMatches(event.getColumn());
      final Todo todo = board.getTodo(event.getTodoId());
      board.getTodos().removeIf(t -> t.getIdentity().equals(todo.getIdentity()));
      final Todo newTodo = new Todo(todo.getIdentity(), todo.getTitle(), todo.getDescription(), Column.of(event.getColumn()), todo.getOwners());
      board.getTodos().add(newTodo);
    };
  }
}
