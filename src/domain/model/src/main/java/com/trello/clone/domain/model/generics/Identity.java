package com.trello.clone.domain.model.generics;

import java.util.UUID;

public class Identity implements IValueObject<String> {
	private final String value;

	public Identity() {
		this.value = UUID.randomUUID().toString();
	}

  private Identity(final String value) {
    this.value = value;
  }

  public static Identity of(final String value) {
    return new Identity(value);
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
