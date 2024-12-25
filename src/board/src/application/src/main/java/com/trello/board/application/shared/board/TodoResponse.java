package com.trello.board.application.shared.board;

import java.util.List;

public class TodoResponse {
  private final String id;
  private final String title;
  private final String description;
  private final String column;
  private final List<OwnerResponse> owners;

  public TodoResponse(String id, String title, String description, String column, List<OwnerResponse> owners) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.column = column;
    this.owners = owners;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getColumn() {
    return column;
  }

  public List<OwnerResponse> getOwners() {
    return owners;
  }
}
