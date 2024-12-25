package com.trello.board.application.addowner;

import com.trello.board.application.shared.board.BoardResponse;
import com.trello.board.application.shared.repositories.IEventRepository;
import com.trello.board.domain.Board;
import com.trello.shared.application.generic.IUseCase;

import static com.trello.board.application.shared.board.BoardMapper.mapToResponse;

public class AddOwnerUseCase implements IUseCase<AddOwnerRequest, BoardResponse> {
  private final IEventRepository eventRepository;

  public AddOwnerUseCase(IEventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  public BoardResponse execute(AddOwnerRequest command) {
    final Board board = Board.from(command.getAggregateId(), eventRepository.findByAggregateId(command.getAggregateId()));
    board.addOwner(command.getName(), command.getEmail(), command.getTodoId());
    board.getUncommittedEvents().forEach(eventRepository::save);
    board.markEventsAsCommitted();

    return mapToResponse(board);
  }
}
