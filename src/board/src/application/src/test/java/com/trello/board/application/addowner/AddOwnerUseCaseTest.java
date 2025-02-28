package com.trello.board.application.addowner;

import com.trello.board.application.shared.board.BoardResponse;
import com.trello.board.application.shared.ports.IEventRepositoryPort;
import com.trello.board.domain.events.BoardCreated;
import com.trello.board.domain.events.ColumnAdded;
import com.trello.board.domain.events.TodoCreated;
import com.trello.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class AddOwnerUseCaseTest {
  private final AddOwnerUseCase useCase;
  private final IEventRepositoryPort eventRepository;

  public AddOwnerUseCaseTest() {
    eventRepository = Mockito.mock(IEventRepositoryPort.class);
    this.useCase = new AddOwnerUseCase(eventRepository);
  }

  @Test
  void execute() {
    Mockito.when(eventRepository.findByAggregateId(Mockito.anyString())).thenReturn(getEvents());
    final AddOwnerRequest request = new AddOwnerRequest("aggregateId", "Jacobo", "jacobo@gmail.com", "0101");
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Test", response.getName());
        Assertions.assertEquals(1, response.getColumns().size());
        Assertions.assertEquals("Column1", response.getColumns().get(0));
        Assertions.assertInstanceOf(BoardResponse.class, response);
      })
      .verifyComplete();
    Mockito.verify(eventRepository).findByAggregateId(Mockito.anyString());
  }

  private Flux<DomainEvent> getEvents() {
    return Flux.just(
      new BoardCreated("Test"),
      new ColumnAdded("Test", 0),
      new TodoCreated("Test", "Description")
    );
  }
}