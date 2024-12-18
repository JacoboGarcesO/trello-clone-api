package com.trello.board.domain.values;

import com.trello.shared.domain.generic.IValueObject;

import static com.trello.board.domain.values.Validator.validateFormat;
import static com.trello.board.domain.values.Validator.validateIfIsEmpty;
import static com.trello.board.domain.values.Validator.validateMaxLength;
import static com.trello.board.domain.values.Validator.validateMinLength;

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
