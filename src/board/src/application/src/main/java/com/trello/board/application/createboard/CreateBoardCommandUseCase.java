package com.trello.board.application.createboard;

import com.trello.board.application.shared.ports.IEventRepositoryPort;
import com.trello.board.domain.Board;
import com.trello.shared.application.generic.ICommandUseCase;
import reactor.core.publisher.Mono;

public class CreateBoardCommandUseCase implements ICommandUseCase<CreateBoardRequest, Mono<CreateBoardResponse>> {
  private final IEventRepositoryPort eventRepository;

  public CreateBoardCommandUseCase(IEventRepositoryPort eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  public Mono<CreateBoardResponse> execute(final CreateBoardRequest request) {
    final Board board = new Board(request.getName());
    board.getUncommittedEvents().forEach(eventRepository::save);
    board.markEventsAsCommitted();

    return Mono.just(
      new CreateBoardResponse(
        board.getIdentity().getValue(),
        board.getName().getValue()
      )
    );
  }
}
