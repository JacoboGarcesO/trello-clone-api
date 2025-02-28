package com.trello.board.application.addtodo;

import com.trello.board.application.shared.board.BoardResponse;
import com.trello.board.application.shared.ports.IEventRepositoryPort;
import com.trello.board.domain.Board;
import com.trello.shared.application.generic.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.trello.board.application.shared.board.BoardMapper.mapToResponse;

public class AddTodoUseCase implements ICommandUseCase<AddTodoRequest, Mono<BoardResponse>> {
  private final IEventRepositoryPort eventRepository;

  public AddTodoUseCase(IEventRepositoryPort eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  public Mono<BoardResponse> execute(final AddTodoRequest request) {
    return eventRepository
      .findByAggregateId(request.getAggregateId())
      .collectList()
      .map(events -> {
        final Board board = Board.from(request.getAggregateId(), events);
        board.addTodo(request.getTitle(), request.getDescription(), request.getId());
        board.getUncommittedEvents().forEach(eventRepository::save);
        board.markEventsAsCommitted();
        return mapToResponse(board);
      });
  }
}
