package com.trello.clone.domain.model.board;

import com.trello.clone.domain.model.board.events.ColumnAdded;
import com.trello.clone.domain.model.board.events.TodoCreated;
import com.trello.clone.domain.model.board.events.OwnerAdded;
import com.trello.clone.domain.model.board.events.StatusChanged;
import com.trello.clone.domain.model.board.events.BoardCreated;
import com.trello.clone.domain.model.board.values.Name;
import com.trello.clone.domain.model.board.values.BoardId;
import com.trello.clone.domain.model.board.values.Status;
import com.trello.clone.domain.model.generics.AggregateRoot;
import com.trello.clone.domain.model.generics.DomainEvent;

import java.util.List;
import java.util.Map;


public class Board extends AggregateRoot<BoardId> {
  private Name name;
  private Map<Integer, Status> columns;
  private List<Todo> todos;

  public Board(final String name) {
    super(new BoardId());
    subscribe(new BoardHandler(this));
    append(new BoardCreated(name)).apply();
  }

  private Board(final BoardId identity) {
    super(identity);
    subscribe(new BoardHandler(this));
  }

  public static Board from(final BoardId identity, final List<DomainEvent> events) {
    Board board = new Board(identity);
    events.forEach(board::append);
    return board;
  }

  private void addColumn(final String name, final Integer index) {
    append(new ColumnAdded(name, index)).apply();
  }

  private void addTodo(final String title, final String description) {
    append(new TodoCreated(title, description)).apply();
  }

  private void addOwner(final String name, final String email, final String todoId) {
    append(new OwnerAdded(name, email, todoId)).apply();
  }

  private void changeStatus(final String status, final String todoId) {
    append(new StatusChanged(status, todoId)).apply();
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Map<Integer, Status> getColumns() {
    return columns;
  }

  public void setColumns(Map<Integer, Status> columns) {
    this.columns = columns;
  }

  public List<Todo> getTodos() {
    return todos;
  }

  public void setTodos(List<Todo> todos) {
    this.todos = todos;
  }
}
