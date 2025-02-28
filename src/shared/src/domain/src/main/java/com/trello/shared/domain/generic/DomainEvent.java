package com.trello.shared.domain.generic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class DomainEvent implements Serializable {
  private LocalDateTime when;
  private String uuid;
  private String type;
  private String aggregateRootId;
  private String aggregateName;
  private Long version;

  protected DomainEvent() {
  }

  protected DomainEvent(final String type){
    this.type = type;
    this.when = LocalDateTime.now();
    this.uuid = UUID.randomUUID().toString();
    this.version = 1L;
  }

  public LocalDateTime getWhen() {
    return when;
  }

  public String getUuid() {
    return uuid;
  }

  public String getType() {
    return type;
  }

  public String getAggregateRootId() {
    return aggregateRootId;
  }

  public void setAggregateRootId(String aggregateRootId) {
    this.aggregateRootId = aggregateRootId;
  }

  public String getAggregateName() {
    return aggregateName;
  }

  public void setAggregateName(String aggregateName) {
    this.aggregateName = aggregateName;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public void setWhen(LocalDateTime when) {
    this.when = when;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public void setType(String type) {
    this.type = type;
  }
}
