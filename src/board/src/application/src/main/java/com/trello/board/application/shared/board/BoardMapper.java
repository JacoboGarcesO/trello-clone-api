package com.trello.board.application.shared.board;

import com.trello.board.domain.Board;
import com.trello.board.domain.values.Owner;

import java.util.Map;
import java.util.stream.Collectors;

public class BoardMapper {
  private BoardMapper() {
  }

  public static BoardResponse mapToResponse(Board board) {
    return new BoardResponse(
      board.getIdentity().getValue(),
      board.getName().getValue(),
      mapToColumns(board),
      board.getTodos().stream().map(item -> new TodoResponse(
        item.getIdentity().getValue(),
        item.getTitle().getValue(),
        item.getDescription().getValue(),
        item.getStatus().getValue(),
        item.getOwners().stream().map(owner -> new OwnerResponse(
          owner.getValue().get(Owner.Fields.NAME.name()),
          owner.getValue().get(Owner.Fields.EMAIL.name())
        )).toList()
      )).toList()
    );
  }

  private static Map<Integer, String> mapToColumns(Board board) {
    return board
      .getColumns()
      .entrySet()
      .stream()
      .collect(Collectors.toMap(Map.Entry::getKey, item -> item.getValue().getValue()));
  }
}
