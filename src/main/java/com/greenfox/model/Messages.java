package com.greenfox.model;

public class Messages {
  private Iterable<Message> messages;
  private Client client;

  public Messages() {
  }

  public Messages(Iterable<Message> messages, Client client) {
    this.messages = messages;
    this.client = client;
  }

  public Iterable<Message> getMessages() {
    return messages;
  }

  public void setMessages(Iterable<Message> messages) {
    this.messages = messages;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }
}
