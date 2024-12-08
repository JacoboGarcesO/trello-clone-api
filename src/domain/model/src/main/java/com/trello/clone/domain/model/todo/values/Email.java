package com.trello.clone.domain.model.todo.values;

import com.trello.clone.domain.model.generics.IValueObject;

public class Email  implements IValueObject<String> {
  private final String value;

  private Email(final String value) {
    this.value = validate(value.trim());
  }

  public static Email of(final String value) {
    return new Email(value);
  }

  @Override
  public final String getValue() {
    return value;
  }

  private String validate(final String value) {
    if (value == null || value.isEmpty()) {
      throw new IllegalArgumentException("Email cannot be empty");
    }

    if (value.length() > 100) {
      throw new IllegalArgumentException("Email cannot be longer than 100 characters");
    }

    if (!value.matches("^(.+)@(\\S+)$")) {
      throw new IllegalArgumentException("Email must be in the format of 'username@domain.com'");
    }

    return value;
  }
}