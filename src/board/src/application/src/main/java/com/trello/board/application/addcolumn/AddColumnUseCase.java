package com.trello.board.application.addcolumn;

import com.trello.board.application.shared.board.BoardResponse;
import com.trello.board.application.shared.repositories.IEventRepository;
import com.trello.board.domain.Board;
import com.trello.shared.application.generic.IUseCase;

import static com.trello.board.application.shared.board.BoardMapper.mapToResponse;

public class AddColumnUseCase implements IUseCase<AddColumnRequest, BoardResponse> {
  private final IEventRepository eventRepository;

  public AddColumnUseCase(IEventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  public BoardResponse execute(final AddColumnRequest command) {
    final Board board = Board.from(command.getAggregateId(), eventRepository.findByAggregateId(command.getAggregateId()));
    board.addColumn(command.getName(), command.getIndex());
    board.getUncommittedEvents().forEach(eventRepository::save);
    board.markEventsAsCommitted();

    return mapToResponse(board);
  }
}
