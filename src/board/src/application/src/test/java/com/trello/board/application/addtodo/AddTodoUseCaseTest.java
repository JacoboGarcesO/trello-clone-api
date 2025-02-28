package com.trello.board.application.addtodo;

import com.trello.board.application.shared.board.BoardResponse;
import com.trello.board.application.shared.ports.IEventRepositoryPort;
import com.trello.board.domain.events.BoardCreated;
import com.trello.board.domain.events.ColumnAdded;
import com.trello.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class AddTodoUseCaseTest {
  private final AddTodoUseCase useCase;
  private final IEventRepositoryPort eventRepository;

  public AddTodoUseCaseTest() {
    eventRepository = Mockito.mock(IEventRepositoryPort.class);
    this.useCase = new AddTodoUseCase(eventRepository);
  }

  @Test
  void execute() {
    Mockito.when(eventRepository.findByAggregateId(Mockito.anyString())).thenReturn(getEvents());
    final AddTodoRequest request = new AddTodoRequest("aggregateId", "Create test", "Create tests for application usecases");

    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        Assertions.assertNotNull(response);
        Assertions.assertEquals(request.getTitle(), response.getTodos().get(0).getTitle());
        Assertions.assertEquals(request.getDescription().length(), response.getTodos().get(0).getDescription().length());
        Assertions.assertEquals("Test", response.getTodos().get(0).getColumn());
        Assertions.assertInstanceOf(BoardResponse.class, response);
      })
      .verifyComplete();
    Mockito.verify(eventRepository).findByAggregateId(Mockito.anyString());
  }

  private Flux<DomainEvent> getEvents() {
    return Flux.just(
      new BoardCreated("Test"),
      new ColumnAdded("Test", 0)
    );
  }
}