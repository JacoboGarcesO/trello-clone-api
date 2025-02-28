package com.trello.board.infra.service.config;

import com.trello.board.application.addcolumn.AddColumnUseCase;
import com.trello.board.application.addowner.AddOwnerUseCase;
import com.trello.board.application.addtodo.AddTodoUseCase;
import com.trello.board.application.changecolumn.ChangeColumnUseCase;
import com.trello.board.application.createboard.CreateBoardCommandUseCase;
import com.trello.board.application.getallboards.GetAllBoardsUseCase;
import com.trello.infra.mongoadapter.adapters.MongoAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
  @Bean
  public CreateBoardCommandUseCase createBoardCommandUseCase(MongoAdapter mongoAdapter) {
    return new CreateBoardCommandUseCase(mongoAdapter);
  }

  @Bean
  public AddColumnUseCase addColumnUseCase(MongoAdapter mongoAdapter) {
    return new AddColumnUseCase(mongoAdapter);
  }

  @Bean
  public AddTodoUseCase addTodoUseCase(MongoAdapter mongoAdapter) {
    return new AddTodoUseCase(mongoAdapter);
  }

  @Bean
  public AddOwnerUseCase addOwnerUseCase(MongoAdapter mongoAdapter) {
    return new AddOwnerUseCase(mongoAdapter);
  }

  @Bean
  public ChangeColumnUseCase changeColumnUseCase(MongoAdapter mongoAdapter) {
    return new ChangeColumnUseCase(mongoAdapter);
  }

  @Bean
  public GetAllBoardsUseCase getAllBoardsUseCase(MongoAdapter mongoAdapter) {
    return new GetAllBoardsUseCase(mongoAdapter);
  }
}
