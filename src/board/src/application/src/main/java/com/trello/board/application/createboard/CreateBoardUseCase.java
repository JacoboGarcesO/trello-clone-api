package com.trello.board.application.createboard;

import com.trello.board.application.shared.repositories.IEventRepository;
import com.trello.board.domain.Board;
import com.trello.shared.application.generic.IUseCase;

public class CreateBoardUseCase implements IUseCase<CreateBoardRequest, CreateBoardResponse> {
  private final IEventRepository eventRepository;

  public CreateBoardUseCase(IEventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  public CreateBoardResponse execute(final CreateBoardRequest command) {
    final Board board = new Board(command.getName());
    board.getUncommittedEvents().forEach(eventRepository::save);
    board.markEventsAsCommitted();

    return new CreateBoardResponse(
      board.getIdentity().getValue(),
      board.getName().getValue()
    );
  }
}
