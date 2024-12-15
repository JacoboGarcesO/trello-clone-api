package com.trello.clone.domain.model.board;

import com.trello.clone.domain.model.board.events.BoardCreated;
import com.trello.clone.domain.model.board.events.ColumnAdded;
import com.trello.clone.domain.model.board.events.OwnerAdded;
import com.trello.clone.domain.model.board.events.StatusChanged;
import com.trello.clone.domain.model.board.events.TodoCreated;
import com.trello.clone.domain.model.board.values.Owner;
import com.trello.clone.domain.model.generics.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardTest {
  private Board board;

  @BeforeEach
  void setUp() {
    board = new Board("Test");
  }

  @Test
  void createBoard() {
    assertEquals("Test", board.getName().getValue());
    assertNotNull(board.getColumns());
    assertNotNull(board.getTodos());
    assertEquals(1, board.getUncommittedEvents().size());
    assertInstanceOf(BoardCreated.class, board.getUncommittedEvents().get(0));
  }

  @Test
  void rebuildBoard() {
    List<DomainEvent> events = new LinkedList<>();
    events.add(new BoardCreated("Test"));
    Board board1 = Board.from("query5hkb3456okay", events);
    assertNotNull(board.getColumns());
    assertNotNull(board.getTodos());
    assertEquals("Test", board.getName().getValue());
    assertEquals(1, board1.getUncommittedEvents().size());
  }

  @Test
  void rebuildBoardWithEventsEmpty() {
    List<DomainEvent> events = new LinkedList<>();
    assertThrows(IllegalStateException.class, () -> Board.from("query5hkb3456okay", events));
  }

  @Test
  void rebuildBoardWithEventsNull() {
    assertThrows(IllegalStateException.class, () -> Board.from("query5hkb3456okay", null));
  }


  @Test
  void rebuildBoardWithTodo() {
    List<DomainEvent> events = new LinkedList<>();
    events.add(new BoardCreated("Test"));
    events.add(new ColumnAdded("Test", 0));
    events.add(new TodoCreated("Test", "Test"));
    Board board1 = Board.from("query5hkb3456okay", events);
    assertEquals(1, board1.getTodos().size());
    assertEquals(3, board1.getUncommittedEvents().size());
  }

  @Test
  void addColumn() {
    board.addColumn("Test", 0);
    assertEquals(1, board.getColumns().size());
    assertEquals(2, board.getUncommittedEvents().size());
    assertInstanceOf(ColumnAdded.class, board.getUncommittedEvents().get(1));
  }

  @Test
  void addColumnWithIndexNegative() {
    assertThrows(IllegalStateException.class, () -> board.addColumn("Test", -1));
    assertEquals(0, board.getColumns().size());
    assertEquals(2, board.getUncommittedEvents().size());
    assertInstanceOf(ColumnAdded.class, board.getUncommittedEvents().get(1));
  }

  @Test
  void addColumnWithIndexDuplicated() {
    board.addColumn("Test1", 0);
    assertThrows(IllegalStateException.class, () -> board.addColumn("Test", 0));
    assertEquals(1, board.getColumns().size());
    assertEquals(3, board.getUncommittedEvents().size());
    assertInstanceOf(ColumnAdded.class, board.getUncommittedEvents().get(2));
  }

  @Test
  void addColumnWithNameDuplicated() {
    board.addColumn("Test1", 0);
    assertThrows(IllegalStateException.class, () -> board.addColumn("Test1", 1));
    assertEquals(1, board.getColumns().size());
    assertEquals(3, board.getUncommittedEvents().size());
    assertInstanceOf(ColumnAdded.class, board.getUncommittedEvents().get(2));
  }

  @Test
  void addTodoWithoutColumns() {
    assertThrows(IllegalStateException.class, () -> board.addTodo("Test", "Test"));

    assertEquals(0, board.getTodos().size());
    assertEquals(2, board.getUncommittedEvents().size());
    assertInstanceOf(TodoCreated.class, board.getUncommittedEvents().get(1));
  }

  @Test
  void addTodoWithColumns() {
    board.addColumn("Test", 0);
    board.addTodo("Test", "Test");

    assertEquals(1, board.getTodos().size());
    assertEquals(3, board.getUncommittedEvents().size());
    assertInstanceOf(TodoCreated.class, board.getUncommittedEvents().get(2));
  }

  @Test
  void addTodoDuplicated() {
    board.addColumn("Test", 0);
    board.addTodo("Test", "Test");
    assertThrows(IllegalStateException.class, () -> board.addTodo("Test", "Test"));

    assertEquals(1, board.getTodos().size());
    assertEquals(4, board.getUncommittedEvents().size());
    assertInstanceOf(TodoCreated.class, board.getUncommittedEvents().get(3));
  }

  @Test
  void addOwner() {
    board.addColumn("Test", 0);
    board.addTodo("Test", "Test");
    board.addOwner("Jacob", "jacobo@trello.com", board.getTodos().get(0).getIdentity().getValue());

    assertEquals(1, board.getTodos().size());
    assertEquals(1, board.getTodos().get(0).getOwners().size());
    assertEquals("Jacob", board.getTodos().get(0).getOwners().get(0).getValue().get(Owner.Fields.NAME.name()));
    assertEquals(4, board.getUncommittedEvents().size());
    assertInstanceOf(OwnerAdded.class, board.getUncommittedEvents().get(3));
  }

  @Test
  void addOwnerWithoutTodo() {
    assertThrows(IllegalStateException.class, () -> board.addOwner("Test", "Test", "Test"));

    assertEquals(0, board.getTodos().size());
    assertEquals(2, board.getUncommittedEvents().size());
    assertInstanceOf(OwnerAdded.class, board.getUncommittedEvents().get(1));
  }

  @Test
  void changeStatus() {
    board.addColumn("To Do", 0);
    board.addColumn("Doing", 1);
    board.addTodo("Test", "Test");
    board.changeStatus("Doing", board.getTodos().get(0).getIdentity().getValue());
    assertEquals(1, board.getTodos().size());
    assertEquals(5, board.getUncommittedEvents().size());
    assertInstanceOf(StatusChanged.class, board.getUncommittedEvents().get(4));
  }

  @Test
  void changeStatusIfNotExist() {
    board.addColumn("To Do", 0);
    board.addColumn("Doing", 1);
    board.addTodo("Test", "Test");
    assertThrows(IllegalStateException.class, () -> board.changeStatus("Done", board.getTodos().get(0).getIdentity().getValue()));
    assertEquals(1, board.getTodos().size());
    assertEquals(5, board.getUncommittedEvents().size());
    assertInstanceOf(StatusChanged.class, board.getUncommittedEvents().get(4));
  }

  @Test
  void markAsCommitted() {
    board.addColumn("To Do", 0);
    board.addColumn("Doing", 1);
    board.addTodo("Test", "Test");
    board.changeStatus("Doing", board.getTodos().get(0).getIdentity().getValue());
    board.markEventsAsCommitted();
    assertEquals(0, board.getUncommittedEvents().size());
  }
}