package com.trello.board.application.shared.board;

public class OwnerResponse {
  private final String name;
  private final String email;

  public OwnerResponse(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }
}
