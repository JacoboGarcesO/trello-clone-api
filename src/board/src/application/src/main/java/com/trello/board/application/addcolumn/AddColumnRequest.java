package com.trello.board.application.addcolumn;

import com.trello.shared.application.generic.Request;

public class AddColumnRequest extends Request {
  private String name;
  private Integer index;

  public AddColumnRequest() {
    super();
  }

  public AddColumnRequest(final String aggregateId, final String name, final Integer index) {
    super(aggregateId);
    this.name = name;
    this.index = index;
  }

  public String getName() {
    return name;
  }

  public Integer getIndex() {
    return index;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }
}
