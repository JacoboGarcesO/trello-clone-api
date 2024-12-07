package com.trello.clone.domain.model.generics;

import java.io.Serializable;

public interface IValueObject<T> extends Serializable {
	T getValue();
}
