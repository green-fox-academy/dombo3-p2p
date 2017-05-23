package com.greenfox.model;
import java.sql.Timestamp;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Message {

  @Id
  private long id;

  @ManyToOne
  private User user;
  private String text;
  private Timestamp timestamp;

  public Message() {}

  public Message(User user, String text) {
    this.user = user;
    this.text = text;
    this.timestamp = new Timestamp(System.currentTimeMillis());
    this.id = 1000000 + (long)(Math.random() * 1000000);
  }

  public Message(User user, String text, Timestamp timestamp) {
    this.user = user;
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

  public User getUser() {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
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
