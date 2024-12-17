package com.trello.domain.generics;

import java.io.Serializable;

public interface IValueObject<T> extends Serializable {
	T getValue();
}
