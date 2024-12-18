package com.trello.shared.domain.generic;

import java.io.Serializable;

public interface IValueObject<T> extends Serializable {
	T getValue();
}
