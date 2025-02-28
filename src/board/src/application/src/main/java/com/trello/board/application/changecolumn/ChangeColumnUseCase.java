package com.trello.board.application.changecolumn;

import com.trello.board.application.shared.board.BoardResponse;
import com.trello.board.application.shared.ports.IEventRepositoryPort;
import com.trello.board.domain.Board;
import com.trello.shared.application.generic.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.trello.board.application.shared.board.BoardMapper.mapToResponse;

public class ChangeColumnUseCase implements ICommandUseCase<ChangeColumnRequest, Mono<BoardResponse>> {
  private final IEventRepositoryPort eventRepository;

  public ChangeColumnUseCase(IEventRepositoryPort eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  public Mono<BoardResponse> execute(ChangeColumnRequest request) {
    return eventRepository
      .findByAggregateId(request.getAggregateId())
      .collectList()
      .map(events -> {
        final Board board = Board.from(request.getAggregateId(), events);
        board.changeColumn(request.getColumn(), request.getTodoId());
        board.getUncommittedEvents().forEach(eventRepository::save);
        board.markEventsAsCommitted();
        return mapToResponse(board);
      });
  }
}
