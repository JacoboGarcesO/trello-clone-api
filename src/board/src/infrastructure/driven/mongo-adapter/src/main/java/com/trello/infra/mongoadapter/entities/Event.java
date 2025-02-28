package com.trello.infra.mongoadapter.entities;

import com.trello.shared.domain.generic.DomainEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "events")
public class Event {
  @Id
  private String id;
  private DomainEvent domainEvent;

  public Event() {
  }

  public Event(DomainEvent domainEvent, String id) {
    this.domainEvent = domainEvent;
    this.id = id;
  }

  public Event(DomainEvent domainEvent) {
    this.domainEvent = domainEvent;
  }

  public DomainEvent getDomainEvent() {
    return domainEvent;
  }

  public void setDomainEvent(DomainEvent domainEvent) {
    this.domainEvent = domainEvent;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
