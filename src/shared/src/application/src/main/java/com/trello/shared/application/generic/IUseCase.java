package com.trello.shared.application.generic;

public interface IUseCase<T extends Request, R> {
  R execute(T command);
}
