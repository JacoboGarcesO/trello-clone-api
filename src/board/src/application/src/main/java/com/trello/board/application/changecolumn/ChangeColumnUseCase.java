package com.trello.board.application.changecolumn;

import com.trello.board.application.shared.board.BoardResponse;
import com.trello.board.application.shared.repositories.IEventRepository;
import com.trello.board.domain.Board;
import com.trello.shared.application.generic.IUseCase;

import static com.trello.board.application.shared.board.BoardMapper.mapToResponse;

public class ChangeColumnUseCase implements IUseCase<ChangeColumnRequest, BoardResponse> {
  private final IEventRepository eventRepository;

  public ChangeColumnUseCase(IEventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  public BoardResponse execute(ChangeColumnRequest command) {
    final Board board = Board.from(command.getAggregateId(), eventRepository.findByAggregateId(command.getAggregateId()));
    board.changeStatus(command.getColumn(), command.getTodoId());
    board.getUncommittedEvents().forEach(eventRepository::save);
    board.markEventsAsCommitted();

    return mapToResponse(board);
  }
}
