package com.trello.domain.board.values;

import com.trello.domain.generics.IValueObject;

import static com.trello.domain.board.values.Validator.validateFormat;
import static com.trello.domain.board.values.Validator.validateIfIsEmpty;
import static com.trello.domain.board.values.Validator.validateMaxLength;
import static com.trello.domain.board.values.Validator.validateMinLength;

public class Column implements IValueObject<String> {
  private final String value;

  private Column(final String value) {
    this.value = validate(value);
  }

  public static Column of(final String value) {
    return new Column(value);
  }

  @Override
  public String getValue() {
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
