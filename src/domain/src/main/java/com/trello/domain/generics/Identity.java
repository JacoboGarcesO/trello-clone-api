package com.trello.domain.generics;

import java.util.UUID;

public abstract class Identity implements IValueObject<String> {
	private final String value;

	protected Identity() {
		this.value = UUID.randomUUID().toString();
	}

  protected Identity(final String value) {
    this.value = value;
  }

	@Override
	public String getValue() {
		return value;
	}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Identity identity = (Identity) o;
    return value.equals(identity.value);
  }
}
