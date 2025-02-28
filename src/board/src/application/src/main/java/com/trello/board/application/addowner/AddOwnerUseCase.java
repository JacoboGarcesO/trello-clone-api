package com.trello.board.application.addowner;

import com.trello.board.application.shared.board.BoardResponse;
import com.trello.board.application.shared.ports.IEventRepositoryPort;
import com.trello.board.domain.Board;
import com.trello.shared.application.generic.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.trello.board.application.shared.board.BoardMapper.mapToResponse;

public class AddOwnerUseCase implements ICommandUseCase<AddOwnerRequest, Mono<BoardResponse>> {
  private final IEventRepositoryPort eventRepository;

  public AddOwnerUseCase(IEventRepositoryPort eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  public Mono<BoardResponse> execute(AddOwnerRequest request) {
    return eventRepository
      .findByAggregateId(request.getAggregateId())
      .collectList()
      .map(events -> {
        final Board board = Board.from(request.getAggregateId(), events);
        board.addOwner(request.getName(), request.getEmail(), request.getTodoId());
        board.getUncommittedEvents().forEach(eventRepository::save);
        board.markEventsAsCommitted();
        return mapToResponse(board);
      });
  }
}
