package com.trello.shared.domain.generic;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public abstract class DomainEvent implements Serializable {
  public final Instant when;
  public final UUID uuid;
  public final String type;
  private String aggregateRootId;
  private String aggregateName;
  private Long version;

  protected DomainEvent(final String type){
    this.type = type;
    this.when = Instant.now();
    this.uuid = UUID.randomUUID();
    this.version = 1L;
  }

  public Instant getWhen() {
    return when;
  }

  public UUID getUuid() {
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
}
