package com.trello.board.application.getallboards;

import com.trello.board.application.shared.board.BoardMapper;
import com.trello.board.application.shared.board.BoardResponse;
import com.trello.board.application.shared.ports.IEventRepositoryPort;
import com.trello.board.domain.Board;
import com.trello.shared.application.generic.IQueryUseCase;
import com.trello.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

public class GetAllBoardsUseCase implements IQueryUseCase<Flux<BoardResponse>> {
  private final IEventRepositoryPort eventRepository;

  public GetAllBoardsUseCase(IEventRepositoryPort eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  public Flux<BoardResponse> execute() {
    return eventRepository
      .findAllAggregates()
      .collectList()
      .map(events -> events.stream().collect(Collectors.groupingBy(DomainEvent::getAggregateRootId)))
      .map(aggregates -> aggregates.entrySet().stream().map(entry -> Board.from(entry.getKey(), entry.getValue())).toList())
      .map(boards -> boards.stream().map(BoardMapper::mapToResponse).toList())
      .flatMapMany(Flux::fromIterable);
  }
}
