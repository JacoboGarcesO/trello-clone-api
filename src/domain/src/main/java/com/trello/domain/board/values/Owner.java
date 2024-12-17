package com.trello.domain.board.values;

import com.trello.domain.generics.IValueObject;

import java.util.HashMap;
import java.util.Map;

import static com.trello.domain.board.values.Validator.validateIfIsEmpty;
import static com.trello.domain.board.values.Validator.validateMaxLength;
import static com.trello.domain.board.values.Validator.validateMinLength;

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
    validateIfIsEmpty(value);
    validateMaxLength(value);
    validateMinLength(value);
    return value;
  }
}
