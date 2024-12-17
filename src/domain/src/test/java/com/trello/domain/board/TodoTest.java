package com.trello.domain.board;

import com.trello.domain.board.values.Column;
import com.trello.domain.board.values.Description;
import com.trello.domain.board.values.Name;
import com.trello.domain.board.values.Owner;
import com.trello.domain.board.values.TodoId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class TodoTest {
  @Test
  void addOwner() {
    Owner owner = Owner.of("Jacobo", "jacobo@trello.com");
    List<Owner> owners = new LinkedList<>();
    owners.add(owner);
    Todo todo = new Todo(new TodoId(), Name.of("Sacar al perro"), Description.of("Sacar al perro a las 4am"), Column.of("To Do"), owners);

    todo.addOwner(Owner.of("Pedro", "pedro@trello.com"));

    Assertions.assertEquals(2, todo.getOwners().size());
    Assertions.assertEquals("To Do", todo.getStatus().getValue());
    Assertions.assertEquals("Sacar al perro a las 4am", todo.getDescription().getValue());
    Assertions.assertEquals("Sacar al perro", todo.getTitle().getValue());
  }

  @Test
  void addMoreThanTwoOwners() {
    List<Owner> owners = new LinkedList<>();
    owners.add(Owner.of("Jacobo", "jacobo@trello.com"));
    owners.add(Owner.of("Pedro", "pedro@trello.com"));
    Todo todo = new Todo(new TodoId(), Name.of("Sacar al perro"), Description.of("Sacar al perro a las 4am"), Column.of("To Do"), owners);

    Assertions.assertThrowsExactly(IllegalStateException.class, () -> todo.addOwner(Owner.of("Mariana", "mariana@trello.com")));
  }

  @Test
  void addOwnerWithSameName() {
    List<Owner> owners = new LinkedList<>();
    owners.add(Owner.of("Jacobo", "jacobo@trello.com"));
    Todo todo = new Todo(new TodoId(), Name.of("Sacar al perro"), Description.of("Sacar al perro a las 4am"), Column.of("To Do"), owners);

    Assertions.assertThrowsExactly(IllegalStateException.class, () -> todo.addOwner(Owner.of("Jacobo", "hola@trello.com")));
  }

  @Test
  void addOwnerWithSameEmail() {
    Todo todo = new Todo(new TodoId(), Name.of("Sacar al perro"), Description.of("Sacar al perro a las 4am"), Column.of("To Do"));
    todo.addOwner(Owner.of("Mariana", "mariana@trello.com"));

    Assertions.assertThrowsExactly(IllegalStateException.class, () -> todo.addOwner(Owner.of("hola", "mariana@trello.com")));
  }
}