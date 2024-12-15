package com.trello.clone.domain.model.board.values;

import com.trello.clone.domain.model.generics.IValueObject;

import static com.trello.clone.domain.model.board.values.Validator.validateFormat;
import static com.trello.clone.domain.model.board.values.Validator.validateIfIsEmpty;
import static com.trello.clone.domain.model.board.values.Validator.validateMaxLength;
import static com.trello.clone.domain.model.board.values.Validator.validateMinLength;

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
