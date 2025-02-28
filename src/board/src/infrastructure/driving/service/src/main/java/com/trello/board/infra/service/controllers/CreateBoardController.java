package com.trello.board.infra.service.controllers;

import com.trello.board.application.createboard.CreateBoardCommandUseCase;
import com.trello.board.application.createboard.CreateBoardRequest;
import com.trello.board.application.createboard.CreateBoardResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/board/create-board")
public class CreateBoardController {
  private final CreateBoardCommandUseCase useCase;

  public CreateBoardController(CreateBoardCommandUseCase useCase) {
    this.useCase = useCase;
  }

  @PostMapping
  public Mono<CreateBoardResponse> createBoard(@RequestBody CreateBoardRequest request) {
    return useCase.execute(request);
  }
}
