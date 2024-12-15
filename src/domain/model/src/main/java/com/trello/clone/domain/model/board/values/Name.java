package com.trello.clone.domain.model.board.values;

import com.trello.clone.domain.model.generics.IValueObject;

import static com.trello.clone.domain.model.board.values.Validator.validateFormat;
import static com.trello.clone.domain.model.board.values.Validator.validateIfIsEmpty;
import static com.trello.clone.domain.model.board.values.Validator.validateMaxLength;
import static com.trello.clone.domain.model.board.values.Validator.validateMinLength;

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
