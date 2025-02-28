package com.trello.board.infra.service.controllers;

import com.trello.board.application.getallboards.GetAllBoardsUseCase;
import com.trello.board.application.shared.board.BoardResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/board/get-all")
public class GetAllBoardsController {
  private final GetAllBoardsUseCase useCase;

  public GetAllBoardsController(GetAllBoardsUseCase useCase) {
    this.useCase = useCase;
  }

  @GetMapping
  public Flux<BoardResponse> execute() {
    return useCase.execute();
  }
}
