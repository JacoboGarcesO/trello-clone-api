package com.trello.clone.domain.model.board.values;

import com.trello.clone.domain.model.generics.IValueObject;

import java.util.HashMap;
import java.util.Map;

public class Owner implements IValueObject<Map<String, String>> {
  public enum Fields {
    NAME,
    EMAIL
  }

  private final String name;
  private final String email;

  private Owner(final String name, final String email) {
    this.name = validate(name);
    this.email = validate(email);
  }

  public static Owner of(final String name, final String email) {
    return new Owner(name, email);
  }

  @Override
  public Map<String, String> getValue() {
    Map<String, String> dictionary = new HashMap<>();
    dictionary.put(Fields.NAME.name(), name);
    dictionary.put(Fields.EMAIL.name(), email);
    return dictionary;
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
