package com.trello.board.application.getboardbyid;

import com.trello.shared.application.generic.Request;

public class GetBoardByIdRequest extends Request {
  public GetBoardByIdRequest(String aggregateId) {
    super(aggregateId);
  }
}
