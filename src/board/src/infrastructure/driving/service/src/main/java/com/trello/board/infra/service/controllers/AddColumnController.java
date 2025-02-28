package com.trello.board.infra.service.controllers;

import com.trello.board.application.addcolumn.AddColumnRequest;
import com.trello.board.application.addcolumn.AddColumnUseCase;
import com.trello.board.application.shared.board.BoardResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/board/add-column")
public class AddColumnController {
  private final AddColumnUseCase useCase;

  public AddColumnController(AddColumnUseCase useCase) {
    this.useCase = useCase;
  }

  @PostMapping
  public Mono<BoardResponse> execute(@RequestBody AddColumnRequest request) {
    return useCase.execute(request);
  }
}
