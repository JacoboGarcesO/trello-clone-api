package com.trello.board.infra.service.controllers;

import com.trello.board.application.changecolumn.ChangeColumnRequest;
import com.trello.board.application.changecolumn.ChangeColumnUseCase;
import com.trello.board.application.shared.board.BoardResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/board/change-column")
public class ChangeColumnController {
  private final ChangeColumnUseCase useCase;

  public ChangeColumnController(ChangeColumnUseCase useCase) {
    this.useCase = useCase;
  }

  @PostMapping
  public Mono<BoardResponse> execute(@RequestBody ChangeColumnRequest request) {
    return useCase.execute(request);
  }
}
