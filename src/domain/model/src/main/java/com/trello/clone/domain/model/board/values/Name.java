package com.trello.clone.domain.model.board.values;

import com.trello.clone.domain.model.generics.IValueObject;

public class Name implements IValueObject<String> {
  private final String value;

  private Name(final String value) {
    this.value = validate(value.trim());
  }

  public static Name of(final String value) {
    return new Name(value);
  }

  @Override
  public final String getValue() {
    return value;
  }

  private String validate(final String value) {
    if (value == null || value.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty");
    }

    if (value.length() > 100) {
      throw new IllegalArgumentException("Name cannot be longer than 100 characters");
    }

    if (!value.matches("[a-zA-Z0-9\\s]+")) {
      throw new IllegalArgumentException("Name can only contain alphanumeric characters and spaces");
    }

    if (value.length() < 3) {
      throw new IllegalArgumentException("Name must be at least 3 characters long");
    }

    return value;
  }
}
