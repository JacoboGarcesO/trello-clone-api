package com.trello.board.application.addtodo;

import com.trello.board.application.shared.board.BoardResponse;
import com.trello.board.application.shared.repositories.IEventRepository;
import com.trello.board.domain.Board;
import com.trello.shared.application.generic.IUseCase;

import static com.trello.board.application.shared.board.BoardMapper.mapToResponse;

public class AddTodoUseCase implements IUseCase<AddTodoRequest, BoardResponse> {
  private final IEventRepository eventRepository;

  public AddTodoUseCase(IEventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  public BoardResponse execute(final AddTodoRequest command) {
    final Board board = Board.from(command.getAggregateId(), eventRepository.findByAggregateId(command.getAggregateId()));
    board.addTodo(command.getTitle(), command.getDescription());
    board.getUncommittedEvents().forEach(eventRepository::save);
    board.markEventsAsCommitted();

    return mapToResponse(board);
  }
}
