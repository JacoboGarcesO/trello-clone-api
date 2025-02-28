package com.trello.board.infra.service.controllers;

import com.trello.board.application.addowner.AddOwnerRequest;
import com.trello.board.application.addowner.AddOwnerUseCase;
import com.trello.board.application.shared.board.BoardResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/board/add-owner")
public class AddOwnerController {
  private final AddOwnerUseCase useCase;

  public AddOwnerController(AddOwnerUseCase useCase) {
    this.useCase = useCase;
  }

  @PostMapping
  public Mono<BoardResponse> execute(@RequestBody AddOwnerRequest request) {
    return useCase.execute(request);
  }
}
