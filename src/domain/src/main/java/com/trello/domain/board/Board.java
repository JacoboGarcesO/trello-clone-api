package com.trello.domain.board;

import com.trello.domain.board.events.BoardCreated;
import com.trello.domain.board.events.ColumnAdded;
import com.trello.domain.board.events.OwnerAdded;
import com.trello.domain.board.events.StatusChanged;
import com.trello.domain.board.events.TodoCreated;
import com.trello.domain.board.values.BoardId;
import com.trello.domain.board.values.Column;
import com.trello.domain.board.values.Name;
import com.trello.domain.board.values.TodoId;
import com.trello.domain.generics.AggregateRoot;
import com.trello.domain.generics.DomainEvent;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Board extends AggregateRoot<BoardId> {
  private Name name;
  private Map<Integer, Column> columns;
  private List<Todo> todos;

  // region Constructors
  public Board(final String name) {
    super(new BoardId());
    subscribe(new BoardHandler(this));
    append(new BoardCreated(name)).apply();
  }

  private Board(final BoardId identity) {
    super(identity);
    subscribe(new BoardHandler(this));
  }
  // endregion

  // region Getters and Setters
  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Map<Integer, Column> getColumns() {
    return columns;
  }

  public void setColumns(Map<Integer, Column> columns) {
    this.columns = columns;
  }

  public List<Todo> getTodos() {
    return todos;
  }

  public void setTodos(List<Todo> todos) {
    this.todos = todos;
  }
  // endregion

  // region Domain Actions
  public void addColumn(final String name, final Integer index) {
    append(new ColumnAdded(name, index)).apply();
  }

  public void addTodo(final String title, final String description) {
    append(new TodoCreated(title, description)).apply();
  }

  public void addOwner(final String name, final String email, final String todoId) {
    append(new OwnerAdded(name, email, todoId)).apply();
  }

  public void changeStatus(final String status, final String todoId) {
    append(new StatusChanged(status, todoId)).apply();
  }
  // endregion

  // region Public Methods
  public void validateIndex(final Integer index) {
    validateNegativity(index);
    validateIfExists(index);
  }

  public Todo getTodo(final String todoId) {
    final Optional<Todo> todo = todos.stream().filter(item -> item.getIdentity().equals(TodoId.of(todoId))).findFirst();

    if (todo.isEmpty()) {
      throw new IllegalStateException("This todo does not exist");
    }

    return todo.get();
  }

  public void validateColumMatches(final String column) {
    final boolean isValidColumn = isColumnExist(column);

    if (!isValidColumn) {
      throw new IllegalStateException("This column does not exist on this board");
    }
  }

  public void validateColumDoesNotMatch(final String column) {
    final boolean isColumnDuplicated = isColumnExist(column);

    if (isColumnDuplicated) {
      throw new IllegalStateException("This column is duplicated");
    }
  }

  public void validateColumnsExist() {
    if (columns.isEmpty()) {
      throw new IllegalStateException("This board does not have any column to add a todo");
    }
  }

  public void validateTodoDuplicated(final String title) {
    final boolean isTodoDuplicated = todos.stream().anyMatch(item -> item.getTitle().getValue().equals(title));

    if (isTodoDuplicated) {
      throw new IllegalStateException("This todo is duplicated");
    }
  }
  // endregion

  // region Private Methods
  private void validateIfExists(Integer index) {
    if (columns.containsKey(index)) {
      throw new IllegalStateException("This column already exists");
    }
  }

  private void validateNegativity(Integer index) {
    if (index < 0) {
      throw new IllegalStateException("Column index must be greater than 0");
    }
  }

  private boolean isColumnExist(final String column) {
    return columns.values().stream().anyMatch(item -> item.getValue().equals(column));
  }
  // endregion

  // region Static Methods
  public static Board from(final String identity, final List<DomainEvent> events) {
    if (events == null || events.isEmpty()) {
      throw new IllegalStateException("The events cannot be null or empty");
    }

    Board board = new Board(BoardId.of(identity));
    events.forEach(event -> board.append(event).apply());
    return board;
  }
  // endregion
}
