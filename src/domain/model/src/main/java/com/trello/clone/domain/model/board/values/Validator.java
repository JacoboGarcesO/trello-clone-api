package com.trello.clone.domain.model.board.values;

public class Validator {
  public static void validateMinLength(final String value) {
    if (value.length() < 3) {
      throw new IllegalArgumentException("The value object must be at least 3 characters long");
    }
  }

  public static void validateFormat(final String value) {
    if (!value.matches("^[a-zA-Z0-9\\s]*$")) {
      throw new IllegalArgumentException("The value object can only contain alphanumeric characters and spaces");
    }
  }

  public static void validateMaxLength(final String value) {
    if (value.length() > 100) {
      throw new IllegalArgumentException("The value object cannot be longer than 100 characters");
    }
  }

  public static void validateIfIsEmpty(final String value) {
    if (value == null || value.isEmpty()) {
      throw new IllegalArgumentException("The value object cannot be empty");
    }
  }
}
