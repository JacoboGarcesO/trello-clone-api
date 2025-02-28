package com.trello.board.application.createboard;

import com.trello.board.application.shared.ports.IEventRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

class CreateBoardUseCaseTest {
  private final CreateBoardCommandUseCase useCase;

  public CreateBoardUseCaseTest() {
    final IEventRepositoryPort eventRepository = Mockito.mock(IEventRepositoryPort.class);
    this.useCase = new CreateBoardCommandUseCase(eventRepository);
  }

  @Test
  void execute() {
    final CreateBoardRequest request = new CreateBoardRequest("Board 1");
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Board 1", response.getName());
        Assertions.assertNotNull(response.getBoardId());
        Assertions.assertInstanceOf(CreateBoardResponse.class, response);
      })
      .verifyComplete();
  }
}