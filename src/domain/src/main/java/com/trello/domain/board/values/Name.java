package com.trello.domain.board.values;

import com.trello.domain.generics.IValueObject;

import static com.trello.domain.board.values.Validator.validateFormat;
import static com.trello.domain.board.values.Validator.validateIfIsEmpty;
import static com.trello.domain.board.values.Validator.validateMaxLength;
import static com.trello.domain.board.values.Validator.validateMinLength;

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
    validateIfIsEmpty(value);
    validateMaxLength(value);
    validateFormat(value);
    validateMinLength(value);
    return value;
  }
}
