package com.trello.clone.domain.model.board.values;

import com.trello.clone.domain.model.generics.IValueObject;

public class Status implements IValueObject<String> {
  private final String value;

  private Status(final String value) {
    this.value = validate(value);
  }

  public static Status of(final String value) {
    return new Status(value);
  }

  @Override
  public String getValue() {
    return value;
  }

  private String validate(final String value) {
    if (value == null || value.isEmpty()) {
      throw new IllegalArgumentException("Status cannot be empty");
    }

    if (value.length() > 100) {
      throw new IllegalArgumentException("Status cannot be longer than 100 characters");
    }

    if (!value.matches("[a-zA-Z0-9\\s]+")) {
      throw new IllegalArgumentException("Status can only contain alphanumeric characters and spaces");
    }

    if (value.length() < 3) {
      throw new IllegalArgumentException("Status must be at least 3 characters long");
    }

    return value;
  }
}
