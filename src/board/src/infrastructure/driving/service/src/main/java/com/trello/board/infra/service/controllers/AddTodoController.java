package com.trello.board.infra.service.controllers;

import com.trello.board.application.addtodo.AddTodoRequest;
import com.trello.board.application.addtodo.AddTodoUseCase;
import com.trello.board.application.shared.board.BoardResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/board/add-todo")
public class AddTodoController {
  private final AddTodoUseCase useCase;

  public AddTodoController(AddTodoUseCase useCase) {
    this.useCase = useCase;
  }

  @PostMapping
  public Mono<BoardResponse> execute(@RequestBody AddTodoRequest request) {
    return useCase.execute(request);
  }
}
