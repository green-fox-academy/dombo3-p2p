package com.greenfox.model;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {

  @Id
  private long id;

  private String username;
  private String text;
  private Timestamp timestamp;

  public Message() {}

  public Message(String username, String text) {
    this.username = username;
    this.text = text;
    this.timestamp = new Timestamp(System.currentTimeMillis());
    this.id = 1000000 + (long)(Math.random() * 1000000);
  }

  public Message(String username, String text, Timestamp timestamp) {
    this.username = username;
    this.text = text;
    this.timestamp = timestamp;
    this.id = 1000000 + (long)(Math.random() * 1000000);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String user)
  {
    this.username = user;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }
}
