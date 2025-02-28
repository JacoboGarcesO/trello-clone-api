package com.trello.board.application.addcolumn;

import com.trello.board.application.shared.board.BoardResponse;
import com.trello.board.application.shared.ports.IEventRepositoryPort;
import com.trello.board.domain.events.BoardCreated;
import com.trello.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class AddColumnUseCaseTest {
  private final AddColumnUseCase useCase;
  private final IEventRepositoryPort eventRepository;

  public AddColumnUseCaseTest() {
    eventRepository = Mockito.mock(IEventRepositoryPort.class);
    this.useCase = new AddColumnUseCase(eventRepository);
  }

  @Test
  void execute() {
    Mockito.when(eventRepository.findByAggregateId(Mockito.anyString())).thenReturn(getEvents());
    final AddColumnRequest request = new AddColumnRequest("aggregateId", "Column1", 0);
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
    return Flux.just(new BoardCreated("Test"));
  }
}