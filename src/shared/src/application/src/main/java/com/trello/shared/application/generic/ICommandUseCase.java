package com.trello.shared.application.generic;

public interface ICommandUseCase<T extends Request, R> {
  R execute(T request);
}
