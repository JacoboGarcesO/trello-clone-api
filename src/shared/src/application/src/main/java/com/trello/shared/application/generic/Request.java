package com.trello.shared.application.generic;

public abstract class Request {
  private String aggregateId;

  protected Request() {
  }

  protected Request(String aggregateId) {
    this.aggregateId = aggregateId;
  }

  public String getAggregateId() {
    return aggregateId;
  }

  public void setAggregateId(String aggregateId) {
    this.aggregateId = aggregateId;
  }
}
