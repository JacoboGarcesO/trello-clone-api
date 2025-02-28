package com.trello.board.application.getboardbyid;

import com.trello.board.application.shared.board.BoardResponse;
import com.trello.board.application.shared.ports.IEventRepositoryPort;
import com.trello.board.domain.Board;
import com.trello.shared.application.generic.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.trello.board.application.shared.board.BoardMapper.mapToResponse;

public class GetBoardByIdUseCase implements ICommandUseCase<GetBoardByIdRequest, Mono<BoardResponse>> {
  private final IEventRepositoryPort eventRepository;

  public GetBoardByIdUseCase(IEventRepositoryPort eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  public Mono<BoardResponse> execute(GetBoardByIdRequest request) {
    return eventRepository
      .findByAggregateId(request.getAggregateId())
      .collectList()
      .map(events -> {
        Board board = Board.from(request.getAggregateId(), events);
        return mapToResponse(board);
      });
  }
}
